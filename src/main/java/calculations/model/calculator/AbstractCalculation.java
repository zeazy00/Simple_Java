package calculations.model.calculator;

import lombok.Getter;

@Getter
public abstract class AbstractCalculation implements Calculation {

    private final CalculationAvailableOperations operationName;

    public AbstractCalculation(CalculationAvailableOperations operationName) {
        this.operationName = operationName;
    }
}
