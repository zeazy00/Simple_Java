package model.calculator;

import org.springframework.stereotype.Service;

@Service
public class MaxCalculator extends  AbstractCalculation{

    public MaxCalculator(int[] input){
        super(input, "Max");
    }

    @Override
    public int execute() {
        return numbers.stream()
                .max(Integer::compare)
                .get();
    }
}
