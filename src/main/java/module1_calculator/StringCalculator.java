package module1_calculator;

public class StringCalculator {

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        String[] numbers = input.split(",");
        int result = 0;

        for (String s : numbers) {
            result += Integer.parseInt(s);
        }

        return result;
    }

}
