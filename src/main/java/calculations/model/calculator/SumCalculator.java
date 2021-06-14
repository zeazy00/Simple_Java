package calculations.model.calculator;

public class SumCalculator extends  AbstractCalculation{

    public SumCalculator(int[] input) {
        super(input, "Sum");
    }

    @Override
    public int execute() {
        return numbers.stream()
                .reduce(0, (x, y) -> x + y);
    }
}
