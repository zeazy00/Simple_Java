package model;

public class ToArrayParser {
    public static int[] parseFromString(String input) {
        int[] res = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            res[i] = Character.getNumericValue(input.charAt(i));
        }

        return res;
    }
}
