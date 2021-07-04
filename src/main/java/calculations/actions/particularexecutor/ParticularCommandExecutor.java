package calculations.actions.particularexecutor;

import calculations.controller.dto.OperationResultDTO;

public interface ParticularCommandExecutor {
    OperationResultDTO execute(String opName, int input);
}
