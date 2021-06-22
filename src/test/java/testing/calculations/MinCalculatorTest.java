package testing.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.MinCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MinCalculatorTest {

    @Test
    void execute() {
        int[] data = {1, 2, 8, 9, 4, 2};
        int expected = Arrays.stream(data)
                             .min()
                             .getAsInt();

        Calculation calc = new MinCalculator();
        calc.setSource(data);
        int res = calc.execute();

        Assertions.assertEquals(res, expected);
    }
}