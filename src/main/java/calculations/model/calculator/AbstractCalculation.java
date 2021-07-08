package calculations.model.calculator;

import lombok.Getter;

@Getter
public abstract class AbstractCalculation implements Calculation {

    private final CalculationAvailableOperations operation;

    public AbstractCalculation(CalculationAvailableOperations operation) {
        this.operation = operation;
    }
}
