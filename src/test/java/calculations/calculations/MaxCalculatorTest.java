package calculations.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.MaxCalculator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxCalculatorTest {

    Calculation calculator = new MaxCalculator();

    @Test
    public void execute() {
        //arrange
        List<Integer> data = Lists.newArrayList(14, 3648, 9987, 411, 366, 7489, 46);

        int expected = 9987;

        //act
        int res = calculator.execute(data);

        //assert
        assertEquals(res, expected);
    }
}