package calculations.model.calculator;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Primary
@Scope("prototype")
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
