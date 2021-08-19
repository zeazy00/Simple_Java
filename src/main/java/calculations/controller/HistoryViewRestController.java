package calculations.controller;

import calculations.controller.dto.OperationResultDTO;
import calculations.controller.dto.filtration.standard.FiltrationArgs;
import calculations.controller.dto.mappers.MathExpToOpResDTOMapper;
import calculations.model.entity.MathExpression;
import calculations.model.entity.QMathExpression;
import calculations.model.repository.MathExpressionRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/math/calculate/history")
public class HistoryViewRestController {

    public static final int PAGE_SIZE = 20;

    private final MathExpressionRepository repository;
    private final MathExpToOpResDTOMapper mapper;

    private final BooleanBuilder builder = new BooleanBuilder();
    private final QMathExpression qMathExp = QMathExpression.mathExpression;

    @GetMapping
    public List<OperationResultDTO> getAllHistory(@PageableDefault(size = PAGE_SIZE,
                                                                   sort = {"operationName"},
                                                                   direction = Sort.Direction.DESC) Pageable pageable) {
        return repository.findAll(pageable)
                         .stream()
                         .map(mapper::mathExpToOpRes)
                         .collect(Collectors.toList());
    }

    @GetMapping("filter")
    public List<OperationResultDTO> getFilteredHistory(@PageableDefault(size = PAGE_SIZE,
                                                                        sort = {"operationName"},
                                                                        direction = Sort.Direction.DESC) Pageable pageable,
                                                       FiltrationArgs filter) {

        return repository.findAll(getPredicateFromFilter(filter), pageable)
                         .stream()
                         .map(mapper::mathExpToOpRes)
                         .collect(Collectors.toList());
    }

    private Predicate getPredicateFromFilter(FiltrationArgs filter) {



        return builder.and(qMathExp.input.like(filter.getInput()))
                      .and(qMathExp.operation.eq(filter.getOperation()))
                      .and(qMathExp.createDate.loe(filter.getFinalDate()))
                      .and(qMathExp.createDate.goe(filter.getInitialDate()));
    }

}
