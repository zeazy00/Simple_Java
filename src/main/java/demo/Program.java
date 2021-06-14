package demo;

import model.DataValidation;
import model.ToArrayParser;
import model.calculator.AbstractCalculation;
import model.calculator.SumCalculator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class Program {
    public static void main(String[] args) throws Exception {
        String input = input();
        int[] data = ToArrayParser.parseFromString(input);

        output(new SumCalculator(data));
    }

    private static void output(AbstractCalculation calculation) {
        System.out.printf("Result of %s operation with %s is %d",
                          calculation.getOperationName(),
                          calculation.getSource(),
                          calculation.execute());
    }

    private static String input() throws Exception {
        System.out.println("Enter number:");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String input = reader.readLine();
        while (DataValidation.validate(input) == false) {
            System.out.println("Invalid input\nTry again!");
            input = reader.readLine();
        }

        return input;
    }
}
