package model.calculator;

public class MinCalculator extends  AbstractCalculation{
    public MinCalculator(int[] input) {
        super(input);
    }

    @Override
    public int execute() {
        return numbers.stream()
                .min(Integer::compareTo)
                .get();
    }
}
