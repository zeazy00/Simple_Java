package demo;

import model.DataValidation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Program {
    public static void main(String[] args) throws Exception {
        String input = input();
        var app = new Logic(input);
        output(app);
    }

    private static void output(Logic app) {
        System.out.printf("Source string is %s\nThere are some results:\n%n",
                app.getSource());

        System.out.printf("Sum of all digits is %d%n",
                app.getSum());

        System.out.printf("Min is %d; Max is %d%n",
                app.getMin(), app.getMax());

        System.out.printf("Avg is %d%n",
                app.getAvg());
    }

    private static String input() throws Exception{
        System.out.println("Enter number:");
        var reader = new BufferedReader(
                new InputStreamReader(System.in));

        var input = reader.readLine();
        while (DataValidation.validate(input) == false){
            System.out.println("Invalid input\nTry again!");
            input = reader.readLine();
        }

        return input;
    }
}
