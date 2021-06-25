package testing.Spring.calculations;

import calculations.Program;
import calculations.model.calculator.MaxCalculator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Program.class)
class MaxCalculatorTest{

    @Autowired
    BeanFactory beanFactory;

    @Test
    public void execute() {

        int[] data = {14, 3648, 9987, 411, 366, 7489, 46};

        int expected = Arrays.stream(data).max().getAsInt();

        MaxCalculator calculator = beanFactory.getBean(MaxCalculator.class);
        calculator.setSource(data);
        int res = calculator.execute();

        assertEquals(res, expected);
    }
}