package calculations.model.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCalculation implements Calculation {

    protected List<Integer> numbers;
    private String source;
    private String operationName;

    public AbstractCalculation(int[] input, String operationName) {
        if (input == null)
            throw new NullPointerException();

        this.operationName = operationName;
        source = "";
        Arrays.stream(input)
                .forEach(x -> source = source + String.valueOf(x));  //получение

        this.numbers = Arrays.stream(input)
                .boxed()
                .collect(Collectors.toList());
    }

    public String getOperationName() {
        return operationName;
    }

    public String getSource() {
        return source;
    }
}
