package calculations.controller;

import calculations.model.calculator.Calculation;
import calculations.model.utils.DataValidation;
import calculations.model.utils.ListUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CalculationController {

    private final List<Calculation> calculations;

    public void start() {
        String input = input();
        List<Integer> data = ListUtil.parseDigitsFromString(input);

        System.out.printf("A number of operations were performed at %s:\n",
                          input);
        calculations.forEach(calc -> {
            int res = calc.execute(data);
            output(res, calc);
        });
    }

    private void output(int result, Calculation calculation) {
        System.out.printf("Result of %s operation is %d\n",
                          calculation.getOperationName(),
                          result);
    }

    public String input() {
        System.out.println("Enter number:");

        String input = "";
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            input = reader.readLine();
            while (!DataValidation.validate(input)) {
                System.out.println("Invalid input\nTry again!");
                input = reader.readLine();
            }
        } catch (IOException exception) {
            log.error("Input error:\n" + exception.getMessage());
        }

        return input;
    }

}
