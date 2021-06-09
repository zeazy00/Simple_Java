package model;

public class DataValidation {

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

}