package model.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCalculation implements Calculation {

    protected List<Integer> numbers;
    private String source;

    public  AbstractCalculation(int[] input){
        if (input == null)
            throw new NullPointerException();

        source = "";
        Arrays.stream(input)
                .forEach(x-> source = source + String.valueOf(x));  //получение

        this.numbers = Arrays.stream(input)
                .boxed()
                .collect(Collectors.toList());
    }
}
