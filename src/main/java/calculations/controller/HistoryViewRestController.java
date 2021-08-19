package calculations.controller;

import calculations.controller.dto.OperationResultDTO;
import calculations.controller.dto.filtration.standard.Filter;
import calculations.controller.dto.filtration.standard.FiltrationArgs;
import calculations.controller.dto.mappers.MathExpToOpResDTOMapper;
import calculations.model.entity.QMathExpression;
import calculations.model.repository.MathExpressionRepository;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/math/calculate/history")
public class HistoryViewRestController {

    public static final int PAGE_SIZE = 20;

    private final MathExpressionRepository repository;
    private final MathExpToOpResDTOMapper mapper;

    private final QMathExpression qMathExp = QMathExpression.mathExpression;

    @GetMapping
    public List<OperationResultDTO> getAllHistory(@PageableDefault(size = PAGE_SIZE) Pageable pageable) {
        return repository.findAll(pageable)
                         .stream()
                         .map(mapper::mathExpToOpRes)
                         .collect(Collectors.toList());
    }

    @GetMapping("filter")
    public List<OperationResultDTO> getFilteredHistory(@PageableDefault(size = PAGE_SIZE) Pageable pageable,
                                                       FiltrationArgs filter) {
        if (filter == null)
            return getAllHistory(pageable);

        List<OperationResultDTO> items = repository.findAll(getPredicateFromFilter(filter), pageable)
                                                   .stream()
                                                   .map(mapper::mathExpToOpRes)
                                                   .collect(Collectors.toList());

        return items;
    }

    private Predicate getPredicateFromFilter(FiltrationArgs filter) {
        Predicate res = Filter.builder()
                              .and(filter.getInput(), qMathExp.input::containsIgnoreCase)
                              .and(filter.getOperation(), qMathExp.operation::eq)
                              .and(filter.getInitialDate(), qMathExp.createDate::goe)
                              .and(filter.getFinalDate(), qMathExp.createDate::loe)
                              .build();
        return res;
    }

}
