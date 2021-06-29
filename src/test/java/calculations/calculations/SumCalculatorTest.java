package calculations.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.SumCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SumCalculatorTest {

    @Test
    public void execute() {

        //arrange
        List<Integer> data = Arrays.stream(new int[]{1, 2, 8, 9, 4, 2})
                                   .boxed()
                                   .collect(Collectors.toList());

        Calculation calc = new SumCalculator();

        int expected = data.stream()
                           .mapToInt(x -> x)
                           .sum();

        //act
        int res = calc.execute(data);

        //assert
        Assertions.assertEquals(res, expected);
    }
}
