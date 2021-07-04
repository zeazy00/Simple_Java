package calculations.controller;

import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.Calculation;
import calculations.model.utils.ListUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@ConditionalOnProperty(value = "console", havingValue = "false")
@RequiredArgsConstructor
public class CalculationRestController {

    private final List<Calculation> calculationList;

    @PostMapping("/math/calculate")
    public List<OperationResultDTO> calculateAllByParam(@PathVariable int input) {
        List<Integer> values = ListUtil.parseDigitsFromInteger(input);

        List<OperationResultDTO> result = new ArrayList<>();
        calculationList.forEach(calc -> {
            int res = calc.execute(values);

            result.add(
                    new OperationResultDTO(calc.getOperationName(),
                                           res));
        });

        return result;
    }

    @PostMapping("/math/calculate/sum")
    public OperationResultDTO calculateSum(@RequestBody int input) {
        String opName = "Sum";
        Calculation calc = calculationList.stream()
                                          .filter(x -> x.getOperationName().equals(opName))
                                          .findFirst().get();

        List<Integer> values = ListUtil.parseDigitsFromInteger(input);
        int res = calc.execute(values);

        return new OperationResultDTO(calc.getOperationName(),
                                      res);
    }
}
