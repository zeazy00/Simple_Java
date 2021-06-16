package testing.Spring.utils;

import calculations.model.utils.ToArrayParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToArrayParserTest {

    @Test
    public void emptyInputParse() {

        String input = "";

        int[] res = ToArrayParser.parseFromString(input);

        assertArrayEquals(res, new int[]{});
    }

    @Test
    public void validInputParse() {

        String input = "145499";

        int[] res = ToArrayParser.parseFromString(input);

        assertArrayEquals(res, new int[]{1, 4, 5, 4, 9, 9});
    }

    @Test
    public void invalidInputParse() {

        String input = "14549a9";

       Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ToArrayParser.parseFromString(input);
        });

        Assertions.assertNotNull(exception);
    }

}