package testing.utils;

import calculations.model.utils.ListUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilTest {

    @Test
    public void emptyInputParse() {

        //arrange
        String input = "";

        //Act
        List<Integer> res = ListUtil.parseDigitsFromString(input);

        //assert
        assertArrayEquals(listToIntArray(res), new int[]{});
    }

    @Test
    public void validInputParse() {

        //arrange
        String input = "145499";

        //Act
        List<Integer> res = ListUtil.parseDigitsFromString(input);

        //assert
        assertArrayEquals(listToIntArray(res), new int[]{1, 4, 5, 4, 9, 9});
    }

    @Test
    public void invalidInputParse() {

        //arrange
        String input = "14549a9";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ListUtil.parseDigitsFromString(input);
        });

        //assert
        Assertions.assertNotNull(exception);
    }

    private static int[] listToIntArray(List<Integer> source) {
        return source.stream()
                     .mapToInt(x -> x)
                     .toArray();
    }

}