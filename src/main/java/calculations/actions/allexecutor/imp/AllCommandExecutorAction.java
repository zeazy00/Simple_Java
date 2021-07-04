package calculations.actions.allexecutor.imp;

import calculations.actions.allexecutor.AllCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.Calculation;
import calculations.model.utils.DataValidation;
import calculations.model.utils.ListUtil;
import calculations.model.validation.postprocess.OutputNumberValidator;
import calculations.model.validation.preprocess.InputNumberValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AllCommandExecutorAction implements AllCommandExecutor {

    List<Calculation> calculationList;
    List<OutputNumberValidator> postProcessValidations;
    List<InputNumberValidator> preProcessValidation;

    @Override
    public List<OperationResultDTO> executeAll(String input) {

        if (!DataValidation.validateInput(input))
            throw new IllegalArgumentException("Input string must contain digits only");

        List<OperationResultDTO> resultDTOS = new ArrayList<>();

        List<Integer> data = ListUtil.parseDigitsFromString(input);
        preProcessValidation.forEach(x -> x.validate(input));

        calculationList.forEach(calculation -> {
            int result = calculation.execute(data);
            postProcessValidations.forEach(val -> val.validate(result));
            resultDTOS.add(new OperationResultDTO(calculation.getOperationName(),
                                                  result));
        });

        return resultDTOS;
    }
}
