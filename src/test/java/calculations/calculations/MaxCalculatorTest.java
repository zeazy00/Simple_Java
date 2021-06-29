package calculations.calculations;

import calculations.model.calculator.Calculation;
import calculations.model.calculator.MaxCalculator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxCalculatorTest {

    @Test
    public void execute() {
        //arrange
        List<Integer> data = Arrays.stream(new int[]{14, 3648, 9987, 411, 366, 7489, 46})
                                   .boxed()
                                   .collect(Collectors.toList());

        Calculation calculator = new MaxCalculator();

        int expected = 9987;

        //act
        int res = calculator.execute(data);

        //assert
        assertEquals(res, expected);
    }
}