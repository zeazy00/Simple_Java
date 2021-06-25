package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculation.avg", havingValue = "true")
public class AvgCalculator extends AbstractCalculation {

    public AvgCalculator() {
        super("Avg");
    }

    @Override
    public int execute(List<Integer> data) {
        int sum = data.stream()
                      .reduce(0, (x, y) -> x + y);

        return sum / data.size();
    }
}
