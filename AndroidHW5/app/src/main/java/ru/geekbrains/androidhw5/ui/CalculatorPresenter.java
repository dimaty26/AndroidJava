package ru.geekbrains.androidhw5.ui;

import ru.geekbrains.androidhw5.domain.Calculator;
import ru.geekbrains.androidhw5.domain.CalculatorImpl;
import ru.geekbrains.androidhw5.domain.Operation;

public class CalculatorPresenter {

    private final CalculatorView view;
    private final Calculator calculator;
    private double argOne = 0.0;
    private double argTwo = 0.0;
    private double result = 0.0;
    private Operation operation = Operation.EMPTY;
    private static final int BASE = 10;
    private boolean isDotPress = false;
    private int divider;

    public CalculatorPresenter(CalculatorView calculatorView, Calculator calculator) {
        this.calculator = calculator;
        this.view = calculatorView;
    }

    public void onDigitPressed(int digit) {
        if (operation == Operation.EMPTY) {
            if (isDotPress) {
                argOne = argOne + digit / (double) divider;
                divider *= BASE;
            } else {
                argOne = argOne * BASE + digit;
            }
        } else {
            if (isDotPress) {
                argTwo = argTwo + digit / (double) divider;
                divider *= BASE;
            } else {
                argTwo = argTwo * BASE + digit;
            }
        }
        view.showResultWithoutEquals();
    }

    public void onOperationPressed(Operation operation) {
        this.operation = operation;
        isDotPress = false;
        view.showResult();
    }

    public void onDotPressed() {
        if (!isDotPress) {
            isDotPress = true;
            divider = BASE;
        }
    }

    public void displayResult() {
        result = calculator.performOperation(argOne, argTwo, operation);
        view.showResult();
        argOne = result;
        argTwo = 0.0;
        result = 0.0;
    }

    public void displayClear() {
        argOne = 0.0;
        argTwo = 0.0;
        result = 0.0;
        operation = Operation.EMPTY;
        isDotPress = false;
        view.showResult();
    }

    public double getArgOne() {
        return argOne;
    }

    public void setArgOne(double argOne) {
        this.argOne = argOne;
    }

    public double getArgTwo() {
        return argTwo;
    }

    public void setArgTwo(double argTwo) {
        this.argTwo = argTwo;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
