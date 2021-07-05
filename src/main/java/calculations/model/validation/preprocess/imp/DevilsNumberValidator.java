package calculations.model.validation.preprocess.imp;

import calculations.model.validation.exceptions.ValidationException;
import calculations.model.validation.preprocess.InputNumberValidator;
import org.springframework.stereotype.Component;

@Component
public class DevilsNumberValidator implements InputNumberValidator {

    private static final String DEVIL_NUMBER = "666";

    @Override
    public void validate(String input) {
        if(input.contains(DEVIL_NUMBER))
            throw new ValidationException("Antichrist detected!!! Bonfire is waiting for you!");
    }
}
