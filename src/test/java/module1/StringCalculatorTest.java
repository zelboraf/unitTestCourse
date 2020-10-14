package module1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    private static StringCalculator calculator;

    @BeforeAll
    static void initTest() {
        calculator = new StringCalculator();
    }

    @Test
    void shouldReturnZeroWhenEmptyInputGiven() {
        assertEquals(0, calculator.add(""));
        assertEquals(0, calculator.add(",,,"));
    }

    @Test
    void shouldReturnNumberWhenNumberGiven() {
        assertEquals(0, calculator.add("0"));
        assertEquals(-1, calculator.add("-1"));
        assertEquals(2, calculator.add("2"));
    }

    @Test
    void shouldReturnSumOfTwoNumbersWhenTwoNumbersGiven() {
        assertEquals(5, calculator.add("2,3"));
        assertEquals(0, calculator.add("1,-1"));
        assertEquals(0, calculator.add("0,0"));
    }

    @Test
    void shouldReturnSumOfThreeNumbersWhenThreeNumbersGiven() {
        assertEquals(9, calculator.add("6,2,1"));
        assertEquals(0, calculator.add("6,-5,-1"));
    }

}