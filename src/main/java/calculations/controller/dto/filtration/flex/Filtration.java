package calculations.controller.dto.filtration.flex;


import calculations.model.calculator.CalculationAvailableOperations;
import calculations.model.entity.MathExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

import static calculations.controller.dto.filtration.flex.FilterFields.*;

@AllArgsConstructor
public class Filtration implements Specification<MathExpression> {

    private final SearchOperation operation;

    @Override
    public Predicate toPredicate(Root<MathExpression> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        switch (operation.getOption()) {
            case EQUALS:
                if (operation.isFieldNameEquals(OPERATION))
                    return operationEqualsPredicate(root, builder);
                else
                    return builder.equal(root.get(operation.getFieldName()),
                                         operation.getFieldValue());
            case CONTAINS:
                if (operation.isFieldNameEquals(INPUT))
                    return builder.like(root.get(operation.getFieldName()),
                                        "%" + operation.getFieldValue().toString() + "%");
                else
                    throw new NotSupportedFilterException("This entry filter is not supported");

            case LESS_THAN:
                if (operation.isFieldNameEquals(INPUT) || operation.isFieldNameEquals(OPERATION))
                    throw new NotSupportedFilterException("This comparison filter is not supported");
                else if (operation.isFieldNameEquals(CREATE_DATE))
                    return lessThanDatePredicate(root, builder);
                else
                    return builder.lessThan(root.get(operation.getFieldName()),
                                            operation.getFieldValue().toString());

            case GREATER_THAN:
                if (operation.isFieldNameEquals(INPUT) || operation.isFieldNameEquals(OPERATION))
                    throw new NotSupportedFilterException("This comparison filter is not supported");
                else if (operation.isFieldNameEquals(CREATE_DATE))
                    return greaterThenDatePredicate(root, builder);
                else
                    return builder.greaterThan(root.get(operation.getFieldName()),
                                               operation.getFieldValue().toString());

            default:
                return null;
        }
    }

    private Predicate operationEqualsPredicate(Root<MathExpression> root, CriteriaBuilder builder) {
        String enumName = operation.getFieldValue().toString();
        CalculationAvailableOperations enumValue = CalculationAvailableOperations.valueOf(enumName);

        return builder.equal(root.get(operation.getFieldName()),
                             enumValue);
    }

    private Predicate lessThanDatePredicate(Root<MathExpression> root,
                                            CriteriaBuilder builder) {
        return builder.lessThan(root.get(operation.getFieldName()),
                                LocalDate.parse(operation.getFieldValue().toString()));
    }

    private Predicate greaterThenDatePredicate(Root<MathExpression> root,
                                               CriteriaBuilder builder) {
        return builder.greaterThan(root.get(operation.getFieldName()),
                                   LocalDate.parse(operation.getFieldValue().toString()));
    }
}