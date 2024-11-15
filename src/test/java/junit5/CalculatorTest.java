package junit5;

import org.example.Calculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private static Calculator calculator;
    private int result;
    private int expectedResult;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Setting up before all test: ");
        calculator = new Calculator();
    }

    @BeforeEach
    void setUpResult() {
        result = 0;
        expectedResult = 0;
    }

    @DisplayName("Testing Sum: ")
    @Test
    void testAddition() {
        result = calculator.add(2, 3);
        expectedResult = 5;
        assertEquals(expectedResult, result, "Addition should return the sum of two number: " + expectedResult);
    }

    @DisplayName("Testing Subtraction: ")
    @Test
    void testSubtraction() {
        result = calculator.subtract(2024, 2002);
        expectedResult = 22;
        assertEquals(expectedResult, result, "Subtraction should return the difference of two number: " + expectedResult);
    }

    @DisplayName("Testing Multiplication: ")
    @Test
    void testMultiplication() {
        result = calculator.multiply(5, 10);
        expectedResult = 50;
        assertEquals(expectedResult, result, "Multiplication should return the product of two number: " + expectedResult);
    }

    @DisplayName("Testing Division: ")
    @Test
    void testDivision() {
        result = calculator.divide(10, 5);
        expectedResult = 2;
        assertEquals(expectedResult, result, "Division should return the quotient of two number: " + expectedResult);
    }

    @DisplayName("Testing Division By Zero: ")
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0), "Division by zero should throw an exception!!");
    }

    @DisplayName("Testing Positive: ")
    @Test
    void testIsPositive() {
        assertTrue(calculator.isPositive(10), "This number should be positive");
    }

    @DisplayName("Testing Negative: ")
    @Test
    void testIsNegative() {
        assertFalse(calculator.isNegative(100), "This number should not be negative");
    }

    @DisplayName("Testing Max: ")
    @Test
    void testFindMax() {
        result = calculator.findMax(2077, 1999);
        expectedResult = 2077;
        assertEquals(expectedResult, result, "The max in two number should be: " + expectedResult);
    }

    //Group Assertions
    @DisplayName("Test with multiple assertions: ")
    @Test
    void testGroupAssertions() {
        assertAll("Multiple Assertions",
                () -> assertEquals(10, calculator.add(5, 5), "5+5 should be 10"),
                () -> assertTrue(calculator.isNegative(-10), "-10 should be negative"),
                () -> assertFalse(calculator.isPositive(-10), "-10 should not be positive")
        );
    }

}
