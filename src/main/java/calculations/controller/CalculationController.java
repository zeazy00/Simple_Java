package calculations.controller;

import calculations.model.utils.DataValidation;
import calculations.model.utils.ToArrayParser;
import calculations.model.calculator.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
public class CalculationController {

    private List<Calculation> calculations;

    @Autowired
    public CalculationController(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    public void start() {
        String input = input();
        int[] data = ToArrayParser.parseFromString(input);

        calculations.forEach(calc -> {
            int res = execute(calc, data);
            output(res, calc);
        });
    }

    public int execute(Calculation calculation, int[] data) {
        calculation.setSource(data);
        return calculation.execute();
    }

    private void output(int result, Calculation calculation) {
        System.out.printf("Result of %s operation with %s is %d\n",
                          calculation.getOperationName(),
                          calculation.getSource(),
                          result);
    }

    public String input() {
        System.out.println("Enter number:");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String input = "";
        try {
            input = reader.readLine();
            while (DataValidation.validate(input) == false) {
                System.out.println("Invalid input\nTry again!");
                input = reader.readLine();
            }
        } catch (IOException exception) {
            System.out.println("Input error:\n" + exception.getMessage());
        }

        return input;
    }

}
