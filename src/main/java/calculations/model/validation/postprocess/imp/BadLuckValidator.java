package calculations.model.validation.postprocess.imp;

import calculations.model.validation.exceptions.PostProcessValidationException;
import calculations.model.validation.postprocess.OutputNumberValidator;
import org.springframework.stereotype.Component;

@Component
public class BadLuckValidator implements OutputNumberValidator {

    private static final int BAD_VALUE = 3;

    @Override
    public void validate(Integer input) {
        if(input == BAD_VALUE)
            throw new PostProcessValidationException("Sorry for your bad luck, man.\n");
    }
}
