package com.wicalc.android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private StringBuilder numbers, temp;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btnAdd, btnSup, btnSub, btnDot, btnMul, btnDiv, btnRes, btnPar1, btnPar2, btnClear;

    private boolean isDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setVariables();

        btn0.setOnClickListener(v -> addNumber(0));
        btn1.setOnClickListener(v -> addNumber(1));
        btn2.setOnClickListener(v -> addNumber(2));
        btn3.setOnClickListener(v -> addNumber(3));
        btn4.setOnClickListener(v -> addNumber(4));
        btn5.setOnClickListener(v -> addNumber(5));
        btn6.setOnClickListener(v -> addNumber(6));
        btn7.setOnClickListener(v -> addNumber(7));
        btn8.setOnClickListener(v -> addNumber(8));
        btn9.setOnClickListener(v -> addNumber(9));

        btnSup.setOnClickListener(v -> removeNumber());
        btnClear.setOnClickListener(v -> clearDisplay());
        btnRes.setOnClickListener(v -> calculate());

        btnDot.setOnClickListener(v -> addDecimal());

        btnAdd.setOnClickListener(v -> addOperation("+"));
        btnMul.setOnClickListener(v -> addOperation("x"));
        btnDiv.setOnClickListener(v -> addOperation("÷"));
        btnSub.setOnClickListener(v -> addOperation("-"));

    }

    private void setVariables() {
        numbers = new StringBuilder();
        temp = new StringBuilder();

        isDecimal = false;

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAdd = findViewById(R.id.btnAdd);
        btnSup = findViewById(R.id.btnSup);
        btnSub = findViewById(R.id.btnSub);
        btnDot = findViewById(R.id.btnDot);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnRes = findViewById(R.id.btnRes);
        btnPar1 = findViewById(R.id.btnPar1);
        btnPar2 = findViewById(R.id.btnPar2);
        btnClear = findViewById(R.id.btnClear);

        ((TextView) findViewById(R.id.txtDisplay)).setText("------");
    }

    private void addNumber(int number) {
        numbers.append(number);
        updateDisplay();
    }

    private void addDecimal() {
        if (!isDecimal) {
            numbers.append(".");
            isDecimal = true;
            updateDisplay();
        }
    }

    private void removeNumber() {
        if (numbers.length() > 0) {
            if (numbers.charAt(numbers.length() - 1) == '.') {
                isDecimal = false;
            }
            numbers.deleteCharAt(numbers.length() - 1);
            updateDisplay();
        }
    }

    private void clearDisplay() {
        numbers = new StringBuilder();
        temp = new StringBuilder();
        isDecimal = false;
        updateDisplay();
    }

    private void addOperation(String operation) {
        if(temp.length() > 0 && numbers.length() > 0) {
            calculate();
            return;
        }
        if(temp.length() == 0 && numbers.length() == 0) {
            return;
        }
        if(numbers.length() == 0) {
            return;
        }
        temp = new StringBuilder(numbers + operation);
        numbers = new StringBuilder();
        updateDisplay();
    }

    private void calculate() {
        if(temp.length() == 0 || numbers.length() == 0) {
            return;
        }
        // Calculate the result
        char operation = temp.charAt(temp.length() - 1);
        double firstPart = Double.parseDouble(temp.substring(0, temp.length() - 1));
        double secondPart = Double.parseDouble(numbers.toString());
        double result = 0;
        switch (operation) {
            case '+':
                // Add the numbers
                result = firstPart + secondPart;
                break;
            case '-':
                // Subtract the numbers
                result = firstPart - secondPart;
                break;
            case 'x':
                // Multiply the numbers
                result = firstPart * secondPart;
                break;
            case '÷':
                // Divide the numbers
                result = firstPart / secondPart;
                break;
        }
        // Display the result
        sendResult(result);
    }

    private void updateDisplay() {
        // Display the numbers in the screen
        ((TextView) findViewById(R.id.txtDisplay)).setText(numbers.toString());
        ((TextView) findViewById(R.id.txtTemp)).setText(temp.toString());
    }

    private void sendResult(Double number) {
        String resultString;
        boolean wasDecimal = (number % 1 != 0); // Check if the result is decimal

        if (!wasDecimal) { // If it's an integer
            resultString = String.valueOf((int) Math.round(number)); // Convert to int
        } else { // If it's a decimal
            resultString = String.valueOf(number);
        }

        clearDisplay();
        numbers = new StringBuilder(resultString); // Store the formatted result
        isDecimal = wasDecimal; // Set isDecimal based on result type
        updateDisplay();
    }
}