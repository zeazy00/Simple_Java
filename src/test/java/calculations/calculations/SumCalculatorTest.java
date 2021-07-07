package calculations.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.SumCalculator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SumCalculatorTest {

    Calculation calc = new SumCalculator();

    @Test
    public void execute() {

        //arrange
        List<Integer> data = Lists.newArrayList(1, 2, 8, 9, 4, 2);
        int expected = 26;

        //act
        int res = calc.execute(data);

        //assert
        Assertions.assertEquals(res, expected);
    }
}
