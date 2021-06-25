package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@ConditionalOnProperty(name = "calculation.sum", havingValue = "true")
public class SumCalculator extends  AbstractCalculation{

    public SumCalculator() {
        super("Sum");
    }

    @Override
    public int execute() {
        return numbers.stream()
                .reduce(0, (x, y) -> x + y);
    }
}
