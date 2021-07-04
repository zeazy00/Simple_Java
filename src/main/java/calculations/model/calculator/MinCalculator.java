package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculation.min", havingValue = "true")
public class MinCalculator extends AbstractCalculation {

    public MinCalculator() {
        super(CalculationAvailableOperations.MinCalc);
    }

    @Override
    public int execute(List<Integer> data) {
        return data.stream()
                   .min(Integer::compareTo)
                   .get();
    }
}
