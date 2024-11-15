package junit5;

import org.example.StringAlgorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringAlgorithmTest {

    private static StringAlgorithm stringAlgorithm;
    private String inputString;
    private String expectedResult;

    private int expectedCount;

    @BeforeAll
    static void setUp() {
        stringAlgorithm = new StringAlgorithm();
    }

    @BeforeEach
    void resetAll() {
        inputString = "";
        expectedResult = "";
        expectedCount = 0;
    }

    @DisplayName("Test String Reversal: ")
    @Test
    void testStringReversal() {
        inputString = "Hello";
        expectedResult = "olleH";

        assertEquals(expectedResult, stringAlgorithm.reverserInputString(inputString), "The input " + inputString + "will reverse to be" + expectedResult);
    }

    @DisplayName("Test fail with String Reversal: ")
    @Test
    void testFailStringReversal() {
        inputString = "Hello";
        expectedResult = "Hello";
        boolean check = expectedResult.equals(stringAlgorithm.reverserInputString(inputString));
        assertFalse(check, "The input " + inputString + "should not reverse to be " + expectedResult);
    }

    @DisplayName("Test Count Words in string: ")
    @Test
    void testCountWords() {
        inputString = "Test JUnit5 \t from Basic" +
                "\n and Java OOP";
        expectedCount = 7;
        assertEquals(expectedCount, stringAlgorithm.countWords(inputString), "The string should have " + expectedCount + " words");
    }

    @DisplayName("Test fail with Count Words in string: ")
    @Test
    void testFailCountWords() {
        inputString = "Test JUnit5 \t from Basic" +
                "\n and Java OOP";
        expectedCount = 100;
        boolean check = (expectedCount == stringAlgorithm.countWords(inputString));
        assertFalse(check, "The count " + expectedCount + " words should be fail");
    }

    @DisplayName("Test Palindrome: ")
    @Test
    void testPalindrome() {
        inputString = "ahha";
        assertTrue(stringAlgorithm.validPalindrome(inputString), inputString + "should be a valid Palindrome");
    }

    @DisplayName("Test fail with Palindrome")
    @Test
    void testFailPalindrome() {
        inputString = "Testing JUNIT 5";
        assertFalse(stringAlgorithm.validPalindrome(inputString), inputString + "should not be a valid Palindrome");
    }

}
