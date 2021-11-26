package ru.geekbrains.androidhw5.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.HashMap;
import java.util.Map;

import ru.geekbrains.androidhw5.R;
import ru.geekbrains.androidhw5.domain.CalculatorImpl;
import ru.geekbrains.androidhw5.domain.Operation;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView argOneField;
    private TextView argTwoField;
    private TextView operatorField;
    private TextView textResult;
    private CalculatorPresenter presenter;
    private final Map<Integer, Integer> digits = new HashMap<>();
    private final Map<Integer, Operation> operators = new HashMap<>();
    private static final String SAVE_ARG_ONE = "SAVE_ARG_ONE";
    private static final String SAVE_ARG_TWO = "SAVE_ARG_TWO";
    private static final String SAVE_RESULT = "SAVE_RESULT";
    private static final String SAVE_OPERATION = "SAVE_OPERATION";
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());
        putKey();
        initTextView();
        showResult();

        View.OnClickListener digitsPress = view -> presenter.onDigitPressed(digits.get(view.getId()));
        View.OnClickListener operationPress = view -> presenter.onOperationPressed(operators.get(view.getId()));
        initKey(digitsPress, operationPress);
        findViewById(R.id.button_dot).setOnClickListener(view -> presenter.onDotPressed());
        findViewById(R.id.button_equal).setOnClickListener(view -> presenter.displayResult());
        findViewById(R.id.button_clear).setOnClickListener(view -> presenter.displayClear());

        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                aSwitch.setChecked(false);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(SAVE_ARG_ONE, presenter.getArgOne());
        outState.putDouble(SAVE_ARG_TWO, presenter.getArgTwo());
        outState.putDouble(SAVE_RESULT, presenter.getResult());
        outState.putSerializable(SAVE_OPERATION, presenter.getOperation());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter.setArgOne(savedInstanceState.getDouble(SAVE_ARG_ONE));
        presenter.setArgTwo(savedInstanceState.getDouble(SAVE_ARG_TWO));
        presenter.setResult(savedInstanceState.getDouble(SAVE_RESULT));
        presenter.setOperation((Operation) savedInstanceState.getSerializable(SAVE_OPERATION));
    }

    @Override
    public void showResult() {
        argOneField.setText(String.valueOf(presenter.getArgOne()));
        argTwoField.setText(String.valueOf(presenter.getArgTwo()));
        operatorField.setText(presenter.getOperation().toString());
        textResult.setText(String.valueOf(presenter.getResult()));
    }

    @Override
    public void showResultWithoutEquals() {
        argOneField.setText(String.valueOf(presenter.getArgOne()));
        argTwoField.setText(String.valueOf(presenter.getArgTwo()));
        operatorField.setText(presenter.getOperation().toString());
    }

    public void putKey() {
        digits.put(R.id.button_0, 0);
        digits.put(R.id.button_1, 1);
        digits.put(R.id.button_2, 2);
        digits.put(R.id.button_3, 3);
        digits.put(R.id.button_4, 4);
        digits.put(R.id.button_5, 5);
        digits.put(R.id.button_6, 6);
        digits.put(R.id.button_7, 7);
        digits.put(R.id.button_8, 8);
        digits.put(R.id.button_9, 9);

        operators.put(R.id.button_plus, Operation.PLUS);
        operators.put(R.id.button_minus, Operation.MINUS);
        operators.put(R.id.button_div, Operation.DIVISION);
        operators.put(R.id.button_multiply, Operation.MULTIPLY);
    }

    public void initTextView() {
        argOneField = findViewById(R.id.argOneField);
        argTwoField = findViewById(R.id.argTwoField);
        operatorField = findViewById(R.id.operationField);
        textResult = findViewById(R.id.resultField);
        aSwitch = findViewById(R.id.mode);
    }

    public void initKey(View.OnClickListener digitsPress, View.OnClickListener operatorsPress) {
        findViewById(R.id.button_0).setOnClickListener(digitsPress);
        findViewById(R.id.button_1).setOnClickListener(digitsPress);
        findViewById(R.id.button_2).setOnClickListener(digitsPress);
        findViewById(R.id.button_3).setOnClickListener(digitsPress);
        findViewById(R.id.button_4).setOnClickListener(digitsPress);
        findViewById(R.id.button_5).setOnClickListener(digitsPress);
        findViewById(R.id.button_6).setOnClickListener(digitsPress);
        findViewById(R.id.button_7).setOnClickListener(digitsPress);
        findViewById(R.id.button_8).setOnClickListener(digitsPress);
        findViewById(R.id.button_9).setOnClickListener(digitsPress);

        findViewById(R.id.button_plus).setOnClickListener(operatorsPress);
        findViewById(R.id.button_minus).setOnClickListener(operatorsPress);
        findViewById(R.id.button_div).setOnClickListener(operatorsPress);
        findViewById(R.id.button_multiply).setOnClickListener(operatorsPress);
    }
}
