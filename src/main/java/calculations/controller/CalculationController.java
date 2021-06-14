package calculations.controller;

import calculations.model.DataValidation;
import calculations.model.ToArrayParser;
import calculations.model.calculator.AbstractCalculation;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class CalculationController {

    private AbstractCalculation calculation;

    @Autowired
    public CalculationController(AbstractCalculation calculation) {
        this.calculation = calculation;
    }

    public void start() {
        String input = input();
        int[] data = ToArrayParser.parseFromString(input);

        int res = execute(data);

        output(res, calculation);
    }

    public int execute(int[] data) {
        calculation.setSource(data);
        return calculation.execute();
    }

    private void output(int result, AbstractCalculation calculation) {
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
