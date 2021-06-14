package calculations.model.calculator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class MinCalculator extends  AbstractCalculation{
    public MinCalculator(int[] input) {
        super(input, "Min");
    }

    @Override
    public int execute() {
        return numbers.stream()
                .min(Integer::compareTo)
                .get();
    }
}
