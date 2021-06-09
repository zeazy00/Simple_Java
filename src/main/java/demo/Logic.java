package demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Logic {
    private List<Integer> numbers;
    private String source;

    public Logic(int[] input) {
        if (input == null)
            throw new NullPointerException();

        source = "";
        Arrays.stream(input)
                .forEach(x-> source = source + String.valueOf(x));  //получение

        this.numbers = Arrays.stream(input)
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
        return getSum() / numbers.size();
    }

    public int getSum() {
        return numbers.stream()
                .reduce(0, (x, y) -> x + y);
    }

    public String getSource() {
        return source;
    }
}
