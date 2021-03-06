package calculations.actions.particularexecutor.imp;

import calculations.actions.particularexecutor.ParticularCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.Calculation;
import calculations.model.calculator.CalculationAvailableOperations;
import calculations.model.utils.DataValidation;
import calculations.model.utils.ListUtil;
import calculations.model.validation.exceptions.MathOperationNotSupportedException;
import calculations.model.validation.postprocess.OutputNumberValidator;
import calculations.model.validation.preprocess.InputNumberValidator;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ParticularCommandExecutorAction implements ParticularCommandExecutor {

    Map<CalculationAvailableOperations, Calculation> calculationMap;
    List<OutputNumberValidator> postProcessValidations;
    List<InputNumberValidator> preProcessValidation;

    @Autowired
    public ParticularCommandExecutorAction(List<Calculation> calculationList,
                                           List<OutputNumberValidator> postProcessValidations,
                                           List<InputNumberValidator> preProcessValidation) {

        this.postProcessValidations = postProcessValidations;
        this.preProcessValidation = preProcessValidation;

        calculationMap = new HashMap<>();

        calculationList.forEach(calc -> {
            calculationMap.put(calc.getOperation(), calc);
        });

    }


    @Override
    public OperationResultDTO execute(CalculationAvailableOperations operation, String input) {

        if (!DataValidation.validateInput(input))
            throw new IllegalArgumentException("Input string must contain digits only");

        preProcessValidation.forEach(x -> x.validate(input));

        Calculation calculation = calculationMap.get(operation);
        if (calculation == null)
            throw new MathOperationNotSupportedException(
                    String.format("Operation %s is not supported",
                                  operation));

        List<Integer> data = ListUtil.parseDigitsFromString(input);
        int result = calculation.execute(data);

        postProcessValidations.forEach(x -> x.validate(result));

        return new OperationResultDTO(operation.getOpName(),
                                      result);
    }
}
