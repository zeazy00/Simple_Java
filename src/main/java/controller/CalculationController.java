package controller;

import model.DataValidation;
import model.ToArrayParser;
import model.calculator.AbstractCalculation;
import model.calculator.MaxCalculator;
import model.calculator.SumCalculator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class CalculationController {

    BeanFactory beanFactory;

    @Autowired
    public CalculationController(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;

        start();
    }

    public void start() {
        String input = input();
        int[] data = ToArrayParser.parseFromString(input);

        AbstractCalculation calculation = beanFactory.getBean(AbstractCalculation.class, data);
        output(calculation);
    }

    private void output(AbstractCalculation calculation) {
        System.out.printf("Result of %s operation with %s is %d",
                          calculation.getOperationName(),
                          calculation.getSource(),
                          calculation.execute());
    }

    private String input() {
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
