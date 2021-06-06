package demo;

public class Logic {
    private int[] numbers;
    private String source;

    public static boolean validate(String input) {
        for (int i = 0; i < input.length(); i++) {
            var currentCh = input.charAt(i);
            if (currentCh < '0' || currentCh > '9')
                return false;
        }
        return true;
    }

    public Logic(String number) {
        numbers = new int[number.length()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = number.charAt(i) - '0';
        }
    }

    public int getMax(){
        return  0;
    }

    public int getMin(){
        return  0;
    }

    public int getAvg(){
        return  0;
    }

    public int getSum(){
        return  0;
    }

    public String getSource() {
        return source;
    }
}
