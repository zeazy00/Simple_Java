package testing.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.SumCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SumCalculatorTest {

    @Test
    public void execute() {

        int[] data = {1, 2, 8, 9, 4, 2};
        int expected = Arrays.stream(data).sum();

        Calculation calc = new SumCalculator();
        calc.setSource(data);
        int res = calc.execute();

        Assertions.assertEquals(res, expected);
    }
}
