package calculations.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.MinCalculator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MinCalculatorTest {

    Calculation calc = new MinCalculator();

    @Test
    void execute() {
        //arrange
        List<Integer> data = Lists.newArrayList(1, 2, 8, 9, 4, 2);

        int expected = 1;

        //act
        int res = calc.execute(data);

        //assert
        Assertions.assertEquals(res, expected);
    }
}