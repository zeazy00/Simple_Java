package calculations.controller;

import calculations.actions.allexecutor.AllCommandExecutor;
import calculations.actions.particularexecutor.ParticularCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.CalculationAvailableOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ConditionalOnProperty(value = "console", havingValue = "false")
@RequiredArgsConstructor
@RequestMapping("/math/calculate")
public class CalculationRestController {

    private final ParticularCommandExecutor particularCommandExecutor;
    private final AllCommandExecutor allCommandExecutor;

    @PostMapping
    public List<OperationResultDTO> calculateAllByParam(@RequestParam String input) {
        return allCommandExecutor.executeAll(input);
    }

    @PostMapping("sum")
    public OperationResultDTO calculateSum(@RequestBody String input) {
        return particularCommandExecutor.execute(CalculationAvailableOperations.SUM.getOpName(),
                                                 input);
    }

    @PostMapping("avg")
    public OperationResultDTO calculateAvg(@RequestBody String input) {
        return particularCommandExecutor.execute(CalculationAvailableOperations.AVG.getOpName(),
                                                 input);
    }

    @PostMapping("max")
    public OperationResultDTO calculateMax(@RequestBody String input) {
        return particularCommandExecutor.execute(CalculationAvailableOperations.MAX.getOpName(),
                                                 input);
    }

    @PostMapping("min")
    public OperationResultDTO calculateMin(@RequestBody String input) {
        return particularCommandExecutor.execute(CalculationAvailableOperations.MIN.getOpName(),
                                                 input);
    }
}
