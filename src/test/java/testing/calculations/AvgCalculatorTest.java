package testing.calculations;

import calculations.model.calculator.AvgCalculator;
import calculations.model.calculator.Calculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class AvgCalculatorTest {

    @Test
    public void execute() {

        //arrange
        List<Integer> data = Arrays.stream(new int[]{1, 2, 8, 9, 4, 2})
                                   .boxed()
                                   .collect(Collectors.toList());

        Calculation calc = new AvgCalculator();

        int expected = (int) data.stream()
                                 .mapToInt(x -> x)
                                 .average()
                                 .getAsDouble();

        //act
        int res = calc.execute(data);

        //assert
        Assertions.assertEquals(res, expected);
    }

}