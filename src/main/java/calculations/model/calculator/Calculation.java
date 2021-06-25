package calculations.model.calculator;

import java.util.List;

public interface Calculation {
    int execute(List<Integer> data);
    String getOperationName();
}
