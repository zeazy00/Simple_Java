package calculations.model.calculator;

import lombok.Getter;

@Getter
public abstract class AbstractCalculation implements Calculation {

    private String operationName;

    public AbstractCalculation(String operationName) {
        this.operationName = operationName;
    }
}
