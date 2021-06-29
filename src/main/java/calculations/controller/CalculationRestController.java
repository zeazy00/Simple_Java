package calculations.controller;

import calculations.model.calculator.Calculation;
import calculations.model.utils.ListUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@RestController
@ConditionalOnProperty(value = "console", havingValue = "false")
@RequiredArgsConstructor
public class CalculationRestController {
    private final List<Calculation> calculationList;

    @PostMapping("/math/calculate")
    String calculateAllByParam(@PathVariable int input) {
        List<Integer> values = ListUtil.parseDigitsFromInteger(input);
        StringBuilder response = new StringBuilder();

        calculationList.forEach(calc -> {
            int res = calc.execute(values);
            response.append(
                    getOutputString(res,
                                    calc.getOperationName())
                           );
        });

        return response.toString();
    }

    private String getOutputString(int res, String opName) {
        return String.format("%s:%d,\n", opName, res);
    }

    @SneakyThrows
    @PostMapping("/math/calculate/sum")
    String calculateSum(@RequestBody int input) {
        String opName = "Sum";
        Calculation calculation = calculationList.stream()
                                                 .filter(x -> x.getOperationName().equals(opName))
                                                 .findFirst()
                                                 .orElseThrow(() -> new OperationNotSupportedException(opName));

        List<Integer> values = ListUtil.parseDigitsFromInteger(input);
        int res = calculation.execute(values);

        return getOutputString(res, opName);
    }
}
