package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "calculation.max", havingValue = "true")
public class MaxCalculator extends AbstractCalculation {

    public MaxCalculator() {
        super(CalculationAvailableOperations.MaxCalc);
    }

    @Override
    public int execute(List<Integer> data) {
        return data.stream()
                   .max(Integer::compare)
                   .get();
    }
}
