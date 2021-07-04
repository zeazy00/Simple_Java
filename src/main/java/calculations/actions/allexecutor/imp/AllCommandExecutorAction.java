package calculations.actions.allexecutor.imp;

import calculations.actions.allexecutor.AllCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllCommandExecutorAction implements AllCommandExecutor {

    @Override
    public List<OperationResultDTO> executeAll(String input) {
        return null;
    }
}
