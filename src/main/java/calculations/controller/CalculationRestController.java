package calculations.controller;

import calculations.actions.allexecutor.AllCommandExecutor;
import calculations.actions.particularexecutor.ParticularCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ConditionalOnProperty(value = "console", havingValue = "false")
@RequiredArgsConstructor
public class CalculationRestController {

    private final ParticularCommandExecutor particularCommandExecutor;
    private final AllCommandExecutor allCommandExecutor;

    @PostMapping("/math/calculate")
    public List<OperationResultDTO> calculateAllByParam(@RequestParam int input) {
        return allCommandExecutor.executeAll(input);
    }

    @PostMapping("/math/calculate/sum")
    public OperationResultDTO calculateSum(@RequestBody int input) {
        String opName = "Sum";

        return particularCommandExecutor.execute(opName, input);
    }
}
