package calculations.actions.particularexecutor.imp;

import calculations.actions.particularexecutor.ParticularCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.Calculation;
import calculations.model.utils.ListUtil;
import calculations.model.validation.exceptions.MathOperationNotSupportedException;
import calculations.model.validation.postprocess.OutputNumberValidator;
import calculations.model.validation.preprocess.InputNumberValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ParticularCommandExecutorAction implements ParticularCommandExecutor {

    List<Calculation> calculationList;
    List<OutputNumberValidator> postProcessValidations;
    List<InputNumberValidator> preProcessValidation;

    @Override
    public OperationResultDTO execute(String opName, int input) throws MathOperationNotSupportedException {

        Calculation calculation = calculationList.stream()
                                                 .filter(x -> x.getOperationName()
                                                               .equals(opName))
                                                 .findFirst()
                                                 .orElseThrow(
                                                         () -> new MathOperationNotSupportedException(
                                                                 String.format("Operation %s is not supported",
                                                                               opName)));
        String inputStr = String.valueOf(input);
        preProcessValidation.forEach(x -> x.validate(inputStr));
        List<Integer> data = ListUtil.parseDigitsFromInteger(input);
        int result = calculation.execute(data);
        postProcessValidations.forEach(x -> x.validate(result));

        return new OperationResultDTO(opName, result);
    }
}
