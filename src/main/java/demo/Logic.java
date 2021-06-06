package demo;

import java.util.List;
import java.util.stream.Collectors;

public class Logic {
    private List<Integer> numbers;
    private String source;

    public static boolean validate(String input) {
        if (input == null)
            return false;

        for (int i = 0; i < input.length(); i++) {
            var currentCh = input.charAt(i);
            if (currentCh < '0' || currentCh > '9')
                return false;
        }
        return true;
    }

    public Logic(String number) {
        if (number == null)
            throw new NullPointerException();

        source = number;
        numbers = number.chars()
                .map(x -> x - '0')
                .boxed()
                .collect(Collectors.toList());

        numbers.stream().forEach(System.out::println);
    }

    public int getMax() {
        return 0;
    }

    public int getMin() {
        return 0;
    }

    public int getAvg() {
        return 0;
    }

    public int getSum() {
        return 0;
    }

    public String getSource() {
        return source;
    }
}
