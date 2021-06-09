package model.calculator;

public class MaxCalculator extends  AbstractCalculation{

    public MaxCalculator(int[] input){
        super(input);
    }

    @Override
    public int execute() {
        return numbers.stream()
                .max(Integer::compare)
                .get();
    }
}
