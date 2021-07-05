package calculations.actions.allexecutor;

import calculations.controller.dto.OperationResultDTO;

import java.util.List;

public interface AllCommandExecutor {
    List<OperationResultDTO> executeAll(String input);
}
