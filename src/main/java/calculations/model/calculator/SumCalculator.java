package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
@ConditionalOnProperty(name = "calculation.sum", havingValue = "true")
public class SumCalculator extends AbstractCalculation {

    public SumCalculator() {
        super(CalculationAvailableOperations.SumCalc);
    }

    @Override
    public int execute(List<Integer> data) {
        return data.stream()
                   .reduce(0, (x, y) -> x + y);
    }
}
