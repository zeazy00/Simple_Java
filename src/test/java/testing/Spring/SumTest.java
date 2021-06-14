package testing.Spring;

import calculations.model.calculator.AbstractCalculation;
import calculations.model.calculator.Calculation;
import calculations.model.calculator.SumCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import calculations.Program;

import java.util.Arrays;

@SpringBootTest(classes = Program.class)
@ContextConfiguration(classes = {SumCalculator.class})
public class SumTest {

    BeanFactory beanFactory;

    @Autowired
    public SumTest(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Test
    public void sumTest() {

        int[] data = {1, 2, 8, 9, 4, 2};
        int expected = Arrays.stream(data).sum();

        AbstractCalculation calc = beanFactory.getBean(SumCalculator.class);
        calc.setSource(data);
        int res = calc.execute();

        Assertions.assertEquals(res, expected);
    }
}
