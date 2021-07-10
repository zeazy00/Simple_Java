package calculations.actions.particularexecutor;

import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.CalculationAvailableOperations;

public interface ParticularCommandExecutor {
    OperationResultDTO execute(CalculationAvailableOperations opName, String input);
}
