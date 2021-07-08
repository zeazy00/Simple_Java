package calculations.calculations;

import calculations.model.calculator.AvgCalculator;
import calculations.model.calculator.Calculation;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AvgCalculatorTest {

    Calculation calc = new AvgCalculator();

    @Test
    public void execute() {


        //arrange
        List<Integer> data = Lists.newArrayList(1, 2, 8, 9, 4, 2);

        int expected = 4;

        //act
        int res = calc.execute(data);

        //assert
        Assertions.assertEquals(res, expected);
    }

}