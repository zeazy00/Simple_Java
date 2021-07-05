package calculations.model.calculator;

public enum CalculationAvailableOperations {
    SUM("Sum"), AVG("Avg"), MIN("Min"), MAX("Max");
    private final String opName;

    CalculationAvailableOperations(String opName) {

        this.opName = opName;
    }


    public String getOpName() {
        return opName;
    }
}
