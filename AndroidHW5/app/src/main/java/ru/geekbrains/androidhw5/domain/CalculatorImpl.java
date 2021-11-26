package ru.geekbrains.androidhw5.domain;

public class CalculatorImpl implements Calculator {
    @Override
    public double performOperation(double argOne, double argTwo, Operation operation) {
        switch (operation) {
            case PLUS:
                return argOne + argTwo;
            case MINUS:
                return argOne - argTwo;
            case MULTIPLY:
                return argOne * argTwo;
            case DIVISION:
                return argOne / argTwo;
        }
        return 0.0;
    }
}
