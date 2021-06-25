package calculations.model.calculator;

public interface Calculation {
    int execute();
    void setSource(int[] data);
    String getOperationName();
    String getSource();
}
