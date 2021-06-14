package calculations.model.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCalculation implements Calculation {

    protected List<Integer> numbers;
    private String source;
    private String operationName;

    public AbstractCalculation(String operationName) {
        this.operationName = operationName;
        numbers = new ArrayList<>();
    }

    public void setSource(int[] input){
        if (input == null)
            throw new NullPointerException();

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
