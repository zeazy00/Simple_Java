package testing.Spring;

import calculations.controller.CalculationController;
import calculations.model.calculator.AbstractCalculation;
import calculations.model.calculator.SumCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import calculations.Program;

import java.util.Arrays;

@SpringBootTest(classes = Program.class)
public class ControllerTest {

    @Autowired
    CalculationController ctrl;

    @Test
    public void checkExecution() {

        int[] data = {1, 14, 23, 44, 78, 96, 452};

//        int res = ctrl.execute(data);

//        Assertions.assertEquals(res, Arrays.stream(data).sum());
    }
}
