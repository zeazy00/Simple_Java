package calculations.controller;

import calculations.actions.allexecutor.AllCommandExecutor;
import calculations.actions.particularexecutor.ParticularCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import calculations.controller.dto.ParticularOperationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static calculations.model.calculator.CalculationAvailableOperations.*;

@RestController
@ConditionalOnProperty(value = "console", havingValue = "false")
@RequiredArgsConstructor
@RequestMapping("/math/calculate")
public class CalculationRestController {

    private final ParticularCommandExecutor particularCommandExecutor;
    private final AllCommandExecutor allCommandExecutor;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<OperationResultDTO> calculateAllByParam(@RequestParam String input) {
        return allCommandExecutor.executeAll(input);
    }

    @PostMapping("particular")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResultDTO calculateParticular(@RequestBody ParticularOperationRequestDTO input) {
        return particularCommandExecutor.execute(input.getOperation(),
                                                 input.getNumberInput());
    }
}
