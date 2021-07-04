package calculations.model.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static List<Integer> parseDigitsFromString(String input) {
        ArrayList<Integer> res = new ArrayList<>(input.length());

        for (int i = 0; i < input.length(); i++) {
            int digit = Character.getNumericValue(input.charAt(i));
            if (digit > 9 || digit < 0)
                throw new IllegalArgumentException("Input string must contain digits only!");

            res.add(digit);
        }

        return res;
    }

    public static List<Integer> parseDigitsFromInteger(Integer input) {
        if (input < 0)
            throw new IllegalArgumentException("Input number must be positive!");

        return parseDigitsFromString(String.valueOf(input));
    }
}
