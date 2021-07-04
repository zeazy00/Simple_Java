package calculations.actions.particularexecutor;

import calculations.controller.dto.OperationResultDTO;
import calculations.model.validation.exceptions.MathOperationNotSupportedException;

public interface ParticularCommandExecutor {
    OperationResultDTO execute(String opName, int input) throws MathOperationNotSupportedException;
}
