package demo;

public class Program {
    public static void main(String[] args) {
        String input = input();
        var app = new Logic(input);
        output(app);
    }

    private static void output(Logic app) {
        System.out.println(String.format("Source string is %s\nThere are some results:\n",
                app.getSource()));

        System.out.println(String.format("Sum of all digits is %d",
                app.getSum()));

        System.out.println(String.format("Min is %d; Max is %d",
                app.getMin(), app.getMax()));

        System.out.println(String.format("Avg is %d",
                app.getAvg()));
    }

    private static String input() {
        System.out.println("Enter number:");
        var input = System.console().readLine();
        while (Logic.validate(input) == false){
            System.out.println("Invalid input\nTry again!");
            input = System.console().readLine();
        }

        return input;
    }
}
