package junit5;

import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParamTest {
    private static Calculator calculator;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Setting up before all tests...");
        calculator = new Calculator();
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Tearing down all tests...");
        calculator = null;
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setting up before each test...");
    }

    @Order(1)
    @DisplayName("Test Prime Numbers with ParameterizedTest")
    @ParameterizedTest(name = "Check if {0} is prime")
    @ValueSource(ints = {2, 3, 4, 5, 16, 17})
    void testIsPrimeParameterized(int number) {
        System.out.println("Testing if " + number + " is prime...");
        if (number == 2 || number == 3 || number == 5 || number == 17) {
            assertTrue(calculator.isPrime(number), number + " should be prime.");
        } else {
            assertFalse(calculator.isPrime(number), number + " should not be prime.");
        }
    }

    @Order(2)
    @DisplayName("Test USCLN (GCD) with ParameterizedTest")
    @ParameterizedTest(name = "Calculate GCD of {0} and {1}, expect {2}")
    @CsvSource({
            "48, 18, 6",
            "12, 15, 3",
            "100, 25, 25",
            "7, 13, 1"
    })
    void testUSCLNParameterized(int a, int b, int expectedResult) {
        System.out.println("Testing GCD of " + a + " and " + b);
        assertEquals(expectedResult, calculator.USCLN(a, b), "GCD of " + a + " and " + b + " should be " + expectedResult);
    }

    @Order(3)
    @DisplayName("Test BSCNN (LCM) with ParameterizedTest")
    @ParameterizedTest(name = "Calculate LCM of {0} and {1}, expect {2}")
    @CsvSource({
            "4, 6, 12",
            "12, 15, 60",
            "100, 25, 100",
            "7, 13, 91"
    })
    void testBSCNNParameterized(int a, int b, int expectedResult) {
        System.out.println("Testing LCM of " + a + " and " + b);
        assertEquals(expectedResult, calculator.BSCNN(a, b), "LCM of " + a + " and " + b + " should be " + expectedResult);
    }

    @Order(4)
    @DisplayName("Test Leap Year with ParameterizedTest")
    @ParameterizedTest(name = "Check if {0} is a leap year")
    @CsvSource({
            "2024, true",
            "2023, false",
            "2000, true",
            "1900, false"
    })
    void testIsLeapYearParameterized(int year, boolean expectedResult) {
        System.out.println("Testing if " + year + " is a leap year...");
        assertEquals(expectedResult, calculator.isLeapYear(year), year + " should " + (expectedResult ? "" : "not ") + "be a leap year.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down after each test...");
    }
}

