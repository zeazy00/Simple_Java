package testing;

import calculations.model.DataValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidationTest {

    @Test
    public void invalidInputString(){

        String input = "12dsd2";

        boolean res = DataValidation.validate(input);

        assertEquals(res, false);
    }

    @Test
    public void emptyInputString(){

        String input = "";

        boolean res = DataValidation.validate(input);

        assertEquals(res, false);
    }

    @Test
    public void validInputString(){

        String input = "24548";

        boolean res = DataValidation.validate(input);

        assertEquals(res, true);
    }

    @Test
    public void nullInputString(){

        String input = null;

        boolean res = DataValidation.validate(input);

        assertEquals(res, false);
    }

}