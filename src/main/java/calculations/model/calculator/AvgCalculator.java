package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "calculation.avg", havingValue = "true")
public class AvgCalculator extends AbstractCalculation {

    public AvgCalculator() {
        super("Avg");
    }

    @Override
    public int execute() {
        int sum = numbers.stream()
                .reduce(0, (x, y) -> x + y);
        return sum / numbers.size();
    }
}
