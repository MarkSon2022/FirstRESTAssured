package junit5;

import org.example.Calculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private static Calculator calculator;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Setting up before all tests...");
        calculator = new Calculator();
    }

    @DisplayName("Testing Prime Numbers")
    @Test
    void testIsPrime() {
        assertTrue(calculator.isPrime(7), "7 should be a prime number");
        assertFalse(calculator.isPrime(10), "10 should not be a prime number");
        assertFalse(calculator.isPrime(1), "1 should not be a prime number");
    }

    @DisplayName("Testing USCLN (GCD)")
    @Test
    void testUSCLN() {
        int result = calculator.USCLN(48, 18);
        int expected = 6;
        assertEquals(expected, result, "The greatest common divisor (GCD) of 48 and 18 should be: " + expected);
    }

    @DisplayName("Testing BSCNN (LCM)")
    @Test
    void testBSCNN() {
        int result = calculator.BSCNN(4, 6);
        int expected = 12;
        assertEquals(expected, result, "The least common multiple (LCM) of 4 and 6 should be: " + expected);
    }

    @DisplayName("Testing Leap Year")
    @Test
    void testIsLeapYear() {
        assertTrue(calculator.isLeapYear(2024), "2024 should be a leap year");
        assertFalse(calculator.isLeapYear(2023), "2023 should not be a leap year");
        assertTrue(calculator.isLeapYear(2000), "2000 should be a leap year");
        assertFalse(calculator.isLeapYear(1900), "1900 should not be a leap year");
    }

    @DisplayName("Group Assertions for Combined Scenarios")
    @Test
    void testGroupAssertions() {
        assertAll("Multiple Assertions",
                () -> assertTrue(calculator.isPrime(11), "11 should be a prime number"),
                () -> assertEquals(8, calculator.USCLN(24, 8), "GCD of 24 and 8 should be 8"),
                () -> assertEquals(30, calculator.BSCNN(10, 15), "LCM of 10 and 15 should be 30"),
                () -> assertFalse(calculator.isLeapYear(1900), "1900 should not be a leap year")
        );
    }

}
