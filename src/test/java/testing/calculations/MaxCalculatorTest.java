package testing.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.MaxCalculator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxCalculatorTest{

    @Test
    public void execute() {

        int[] data = {14, 3648, 9987, 411, 366, 7489, 46};

        int expected = Arrays.stream(data).max().getAsInt();

        Calculation calculator = new MaxCalculator();
        calculator.setSource(data);
        int res = calculator.execute();

        assertEquals(res, expected);
    }
}