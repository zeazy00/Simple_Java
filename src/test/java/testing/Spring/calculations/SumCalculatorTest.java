package testing.Spring.calculations;

import calculations.Program;
import calculations.model.calculator.AbstractCalculation;
import calculations.model.calculator.SumCalculator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest(classes = Program.class)
public class SumCalculatorTest {

    @Autowired
    BeanFactory beanFactory;

    @Test
    public void execute() {

        int[] data = {1, 2, 8, 9, 4, 2};
        int expected = Arrays.stream(data).sum();

        AbstractCalculation calc = beanFactory.getBean(SumCalculator.class);
        calc.setSource(data);
        int res = calc.execute();

        Assertions.assertEquals(res, expected);
    }
}
