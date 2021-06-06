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
    }

    public int getMax() {
        return numbers.stream()
                .max(Integer::compare)
                .get();
    }

    public int getMin() {
        return numbers.stream()
                .min(Integer::compareTo)
                .get();
    }

    public int getAvg() {
        var sum = numbers.stream()
                .reduce(0, (x, y) -> x + y);

        return sum / numbers.size();
    }

    public int getSum() {
        return numbers.stream()
                .reduce(0, (x, y) -> x + y);
    }

    public String getSource() {
        return source;
    }
}
