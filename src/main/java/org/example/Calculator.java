package org.example;

public class Calculator {

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }


    public int divide(int num1, int num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return num1 / num2;
    }

    public boolean isPositive(int num) {
        return num > 0;
    }

    public boolean isNegative(int num) {
        return num < 0;
    }

    public int findMax(int num1, int num2) {
        return (num1 > num2) ? num1 : num2;
    }

}
