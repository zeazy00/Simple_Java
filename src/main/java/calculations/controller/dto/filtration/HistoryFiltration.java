package calculations.controller.dto.filtration;


import calculations.model.calculator.CalculationAvailableOperations;
import calculations.model.entity.MathExpression;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class HistoryFiltration implements Specification<MathExpression> {

    private final HistorySearchOperation operation;

    @Override
    public Predicate toPredicate(Root<MathExpression> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        switch (operation.getOption()) {

            case EQUALS:
                return builder.equal(root.get(operation.getFieldName()),
                                     operation.getFieldValue());
            case CONTAINS:
                return builder.like(root.get(operation.getFieldName()),
                                    operation.getFieldValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.<String>get(operation.getFieldName()),
                                        operation.getFieldValue().toString());

            case GREATER_THAN:
                break;

            default:
                return null;
        }

        return null;
    }
}
