package calculations.controller;

import calculations.actions.allexecutor.AllCommandExecutor;
import calculations.actions.particularexecutor.ParticularCommandExecutor;
import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.CalculationAvailableOperations;
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
    public List<OperationResultDTO> calculateAllByParam(@RequestParam String input) {
        return allCommandExecutor.executeAll(input);
    }

    @PostMapping("/math/calculate/sum")
    public OperationResultDTO calculateSum(@RequestBody String input) {
        return particularCommandExecutor.execute(CalculationAvailableOperations.SumCalc,
                                                 input);
    }

//    @PostMapping("/math/calculate/sum")
//    public OperationResultDTO calculateAvg(@RequestBody String input) {
//        String opName = "Sum";
//
//        return particularCommandExecutor.execute(opName, input);
//    }
//
//    @PostMapping("/math/calculate/sum")
//    public OperationResultDTO calculateMax(@RequestBody String input) {
//        String opName = "Sum";
//
//        return particularCommandExecutor.execute(opName, input);
//    }
//
//    @PostMapping("/math/calculate/sum")
//    public OperationResultDTO calculateMin(@RequestBody String input) {
//        String opName = "Sum";
//
//        return particularCommandExecutor.execute(opName, input);
//    }
}
