package model;

public class DataValidation {

    public static boolean validate(String input) {
        if (input == null || input.isBlank())
            return false;

        for (int i = 0; i < input.length(); i++) {
            char currentCh = input.charAt(i);
            if (currentCh < '0' || currentCh > '9')
                return false;
        }

        return true;
    }

}
