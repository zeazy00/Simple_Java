package calculations.model.calculator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculation.max", havingValue = "true")
public class MaxCalculator extends  AbstractCalculation{

    public MaxCalculator(){
        super("Max");
    }

    @Override
    public int execute(List<Integer> data) {
        return data.stream()
                .max(Integer::compare)
                .get();
    }
}
