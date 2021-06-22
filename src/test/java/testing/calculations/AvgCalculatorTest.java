package testing.calculations;

import calculations.model.calculator.AvgCalculator;
import calculations.model.calculator.Calculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AvgCalculatorTest {

    @Test
    public void execute() {

        int[] data = {1, 2, 8, 9, 4, 2};
        int expected = (int)Arrays.stream(data)
                                  .average()
                                  .getAsDouble();

        Calculation calc = new AvgCalculator();
        calc.setSource(data);
        int res = calc.execute();

        Assertions.assertEquals(res, expected);
    }

}