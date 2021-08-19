package calculations.controller;

import calculations.controller.dto.OperationResultDTO;
import calculations.controller.dto.filtration.flex.Filtration;
import calculations.controller.dto.filtration.flex.SearchOperation;
import calculations.controller.dto.mappers.MathExpToOpResDTOMapper;
import calculations.model.entity.MathExpression;
import calculations.model.repository.MathExpressionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@ConditionalOnProperty(value = "complex-history", havingValue = "true")
@RequestMapping("/math/calculate/history")
public class ComplexHistoryViewRestController {

    private final MathExpressionRepository repository;
    private final MathExpToOpResDTOMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationResultDTO> getFilteredPage(@RequestBody SearchOperation[] operationsDTO) {

        List<OperationResultDTO> result = repository.findAll(uniteAll(operationsDTO))
                                                    .stream()
                                                    .map(mapper::mathExpToOpRes)
                                                    .collect(Collectors.toList());
        return result;
    }

    private Specification<MathExpression> uniteAll(SearchOperation[] operations) {
        if (operations.length == 0)
            return null;

        Filtration tmp = new Filtration(operations[0]);

        Specification<MathExpression> result = tmp.and(tmp);

        for (int i = 1; i < operations.length; i++) {
            Filtration current = new Filtration(operations[i]);
            result = result.and(current);
        }

        return result;
    }
}