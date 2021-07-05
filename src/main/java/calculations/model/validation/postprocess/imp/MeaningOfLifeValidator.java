package calculations.model.validation.postprocess.imp;

import calculations.model.validation.exceptions.PostProcessValidationException;
import calculations.model.validation.postprocess.OutputNumberValidator;
import org.springframework.stereotype.Component;

@Component
public class MeaningOfLifeValidator implements OutputNumberValidator {

    private static final int MEANING_OF_LIFE = 42;

    @Override
    public void validate(Integer input) {
        if(input == MEANING_OF_LIFE)
            throw new PostProcessValidationException("Congratulations! Now life finally has some sense!");
    }
}
