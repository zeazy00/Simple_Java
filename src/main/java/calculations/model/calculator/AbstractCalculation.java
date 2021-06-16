package calculations.model.calculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public abstract class AbstractCalculation implements Calculation {

    @Getter(AccessLevel.NONE)
    protected List<Integer> numbers;
    private String source;
    private String operationName;

    public AbstractCalculation(String operationName) {
        this.operationName = operationName;
        numbers = new ArrayList<>();
    }

    @Override
    public void setSource(int[] input){
        if (input == null)
            throw new NullPointerException();

        source = "";
        Arrays.stream(input)
              .forEach(x -> source = source + String.valueOf(x));

        this.numbers = Arrays.stream(input)
                             .boxed()
                             .collect(Collectors.toList());
    }
}
