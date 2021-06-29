package calculations.controller;

import calculations.model.calculator.Calculation;
import calculations.model.outputprovider.OutputProvider;
import calculations.model.utils.DataValidation;
import calculations.model.utils.ListUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
@RequiredArgsConstructor
@ConditionalOnProperty(value = "console", havingValue = "true")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CalculationConsoleController {

    List<Calculation> calculations;
    OutputProvider outputProvider;

    public void start() {
        String input = input();
        List<Integer> data = ListUtil.parseDigitsFromString(input);

        String displayInputMessage = String.format("A number of operations were performed at %s:\n",
                                                   input);

        outputProvider.output(displayInputMessage);
        calculations.forEach(calc -> {
            int res = calc.execute(data);
            outputResult(res, calc);
        });
    }

    private void outputResult(int result, Calculation calculation) {
        String message = String.format("Result of %s operation is %d\n",
                                       calculation.getOperationName(),
                                       result);

        outputProvider.output(message);
    }

    private String input() {
        outputProvider.output("Enter number:\n");

        String input = "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {

            input = reader.readLine();
            while (!DataValidation.validateInput(input)) {
                outputProvider.output("Invalid input\nTry again!\n");
                input = reader.readLine();
            }
        } catch (IOException exception) {
            log.error("Input error:\n" + exception.getMessage());
        }

        return input;
    }

}
