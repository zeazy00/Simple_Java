package calculations.model.utils;

public class ToArrayParser {
    public static int[] parseFromString(String input) {
        int[] res = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            int digit = Character.getNumericValue(input.charAt(i));
            if (digit > 9 || digit < 0)
                throw new IllegalArgumentException("Input string must contain digits only!");

            res[i] = digit;
        }

        return res;
    }
}
