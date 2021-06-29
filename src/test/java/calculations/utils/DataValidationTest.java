package calculations.utils;

import calculations.model.utils.DataValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidationTest {

    @Test
    public void invalidInputString(){

        //arrange
        String input = "12dsd2";

        //act
        boolean res = DataValidation.validateInput(input);

        //assert
        assertFalse(res);
    }

    @Test
    public void emptyInputString(){

        //arrange
        String input = "";

        //act
        boolean res = DataValidation.validateInput(input);

        //assert
        assertFalse(res);
    }

    @Test
    public void validInputString(){

        //arrange
        String input = "24548";

        //act
        boolean res = DataValidation.validateInput(input);

        //assert
        assertTrue(res);
    }

    @Test
    public void nullInputString(){

        //arrange
        String input = null;

        //act
        boolean res = DataValidation.validateInput(input);

        //assert
        assertFalse(res);
    }

}