package junit5;

import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParamTest {
    private static Calculator calculator;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Setting up before all test");
        calculator = new Calculator();
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setting up before each test ...");
    }


    @Order(1)
    @DisplayName("Test Addition With ParameterizedTest")
    @ParameterizedTest(name = "Add {0} and {1}, expect {2}")
    @CsvSource({
            "1,2,3",
            "2,3,5",
            "-1,1,0",
            "-5,-5,-10"
    })
    void testAdditionParameterized(int a, int b, int expectedResult) {
        System.out.println("Test Addition With ParameterizedTest");
        Assertions.assertEquals(expectedResult, calculator.add(a, b), "Addition result of " + a + " and " + b + " should be " + expectedResult);
    }

    @Order(2)
    @DisplayName("Test Subtraction With ParameterizedTest")
    @ParameterizedTest(name = "Subtract {1} from {0}, expect {2}")
    @CsvSource({
            "5,3,2",
            "20,10,10",
            "0,0,0",
            "3,5,-2"
    })
    void testSubtractionParameterized(int a, int b, int expectedResult) {
        System.out.println("Test Subtraction With ParameterizedTest");
        Assertions.assertEquals(expectedResult, calculator.subtract(a, b), "Subtraction result of " + a + " and " + b + " should be " + expectedResult);
    }

    @Order(3)
    @DisplayName("Test Positive With ParameterizedTest")
    @ParameterizedTest(name = "Check if {0} is positive")
    @ValueSource(ints = {1, 2, 10, Integer.MAX_VALUE})
    void testIsPositiveParameterized(int number) {
        System.out.println("Test Positive With ParameterizedTest");
        Assertions.assertTrue(calculator.isPositive(number), number + "should be positive...");
    }

    @Order(4)
    @DisplayName("Test Find Max With ParameterizedTest")
    @ParameterizedTest(name = "Find max between {0} and {1}, expect {2}")
    @MethodSource("provideMaxTestArguments")
    void testFindMaxParameterizedTest(int a, int b, int expectedResult) {
        System.out.println("Test Find Max With ParameterizedTest");
        Assertions.assertEquals(expectedResult, calculator.findMax(a, b), "Max of " + a + " and " + b + " should be " + expectedResult);
    }

    static Stream<Arguments> provideMaxTestArguments() {
        return Stream.of(
                Arguments.of(3, 5, 5),
                Arguments.of(10, 10, 10),
                Arguments.of(-1, -2, -1),
                Arguments.of(0, 0, 0)
        );
    }


    @AfterEach
    void tearDown() {
        System.out.println("Tearing down after each test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Tearing down all test ....");
        calculator = null;
    }
}

