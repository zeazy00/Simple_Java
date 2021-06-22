package calculations.controller;

import calculations.model.utils.DataValidation;
import calculations.model.utils.ToArrayParser;
import calculations.model.calculator.Calculation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
public class CalculationController {

    private List<Calculation> calculations;

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

    public int execute(String name, int[] data) {
        try {
            Calculation calculation = calculations.stream()
                                                  .filter(x -> x.getOperationName().equals(name))
                                                  .findFirst().get();

            return execute(calculation, data);
        } catch (NoSuchElementException ex) {
            throw ex;
        }
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
