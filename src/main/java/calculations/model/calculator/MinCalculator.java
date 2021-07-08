package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "calculation.min", havingValue = "true")
public class MinCalculator extends AbstractCalculation {

    public MinCalculator() {
        super(CalculationAvailableOperations.MIN);
    }

    @Override
    public int execute(List<Integer> data) {
        return data.stream()
                   .min(Integer::compareTo)
                   .get();
    }
}
