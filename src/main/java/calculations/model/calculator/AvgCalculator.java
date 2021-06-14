package calculations.model.calculator;

import org.springframework.stereotype.Service;

@Service
public class AvgCalculator extends AbstractCalculation {

    public AvgCalculator(int[] input) {
        super(input, "Avg");
    }

    @Override
    public int execute() {
        int sum = numbers.stream()
                .reduce(0, (x, y) -> x + y);
        return sum / numbers.size();
    }
}
