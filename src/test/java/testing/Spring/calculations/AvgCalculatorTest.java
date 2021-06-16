package testing.Spring.calculations;

import calculations.Program;
import calculations.model.calculator.AvgCalculator;
import calculations.model.calculator.Calculation;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest(classes = Program.class)
class AvgCalculatorTest {

    @Autowired
    BeanFactory beanFactory;

    @Test
    public void execute() {

        int[] data = {1, 2, 8, 9, 4, 2};
        int expected = (int)Arrays.stream(data)
                                  .average()
                                  .getAsDouble();

        Calculation calc = beanFactory.getBean(AvgCalculator.class);
        calc.setSource(data);
        int res = calc.execute();

        Assertions.assertEquals(res, expected);
    }

}