package com.example.calculatorapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstOperand = 0;
    private double secondOperand = 0;
    private String operator = "";
    private boolean isSecondOperand = false;
    private String currentInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editText);
        display.setBackgroundColor(Color.DKGRAY); // Set display background color

        // Set listeners for all buttons
        setButtonListeners();
    }

    private void setButtonListeners() {
        // Numbers
        int[] numberButtons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int id : numberButtons) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onNumberClick);
            button.setBackgroundColor(Color.BLUE); // Set button background color
            button.setTextColor(Color.WHITE); // Set button text color
        }

        // Operations
        int[] operatorButtons = {
                R.id.buttonAdd, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide
        };

        for (int id : operatorButtons) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onOperatorClick);
            button.setBackgroundColor(Color.CYAN); // Set operator button background color
            button.setTextColor(Color.BLACK); // Set operator button text color
        }

        // Special buttons
        Button equalButton = findViewById(R.id.buttonEqual);
        equalButton.setOnClickListener(this::onEqualClick);
        equalButton.setBackgroundColor(Color.GREEN); // Set equal button background color
        equalButton.setTextColor(Color.WHITE); // Set equal button text color

        Button clearButton = findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(v -> clearDisplay());
        clearButton.setBackgroundColor(Color.RED); // Set clear button background color
        clearButton.setTextColor(Color.WHITE); // Set clear button text color
    }

    // Handle number button clicks
    private void onNumberClick(View v) {
        Button b = (Button) v;
        currentInput += b.getText().toString();
        display.setText(currentInput);
    }

    // Handle operator button clicks
    private void onOperatorClick(View v) {
        Button b = (Button) v;
        firstOperand = Double.parseDouble(currentInput);  // Store the first operand
        operator = b.getText().toString();  // Set the operator
        currentInput = "";  // Reset input for the next number
        isSecondOperand = true;
    }

    // Handle the equal button click
    private void onEqualClick(View v) {
        secondOperand = Double.parseDouble(currentInput);  // Store the second operand
        double result = performCalculation();  // Perform the calculation
        display.setText(String.valueOf(result));  // Display the result
        currentInput = String.valueOf(result);  // Store the result for further operations
    }

    // Perform the calculation based on the operator
    private double performCalculation() {
        double result = 0;
        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                break;
        }
        clearOperands(); // Reset after calculation
        return result;
    }

    // Clear the current operands and operator
    private void clearOperands() {
        firstOperand = 0;
        secondOperand = 0;
        operator = "";
        isSecondOperand = false;
    }

    // Clear the display and reset calculator state
    private void clearDisplay() {
        currentInput = "";
        clearOperands();  // Reset the calculator state
        display.setText("0");  // Clear the display
    }
}
