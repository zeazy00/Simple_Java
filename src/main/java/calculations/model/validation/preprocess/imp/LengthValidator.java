package calculations.model.validation.preprocess.imp;

import calculations.model.validation.exceptions.ValidationException;
import calculations.model.validation.preprocess.InputNumberValidator;
import org.springframework.stereotype.Component;

@Component
public class LengthValidator implements InputNumberValidator {

    public static final int MAX_LENGTH = 100;

    @Override
    public void validate(String input) {
        if (input.length() >= MAX_LENGTH)
            throw new ValidationException(
                    String.format("Input number can't be bigger than %d signs",
                                  MAX_LENGTH));
    }
}
