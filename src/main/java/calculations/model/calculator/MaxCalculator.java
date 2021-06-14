package calculations.model.calculator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class MaxCalculator extends  AbstractCalculation{

    public MaxCalculator(){
        super("Max");
    }

    @Override
    public int execute() {
        return numbers.stream()
                .max(Integer::compare)
                .get();
    }
}
