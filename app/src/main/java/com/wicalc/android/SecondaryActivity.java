package com.wicalc.android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class SecondaryActivity extends AppCompatActivity {

    private StringBuilder expression, temp;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btnAdd, btnSup, btnSub, btnDot, btnMul, btnDiv, btnRes, btnPar1, btnPar2, btnClear;

    private boolean isDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondary);

        setVariables();

        btn0.setOnClickListener(v -> addChar('0'));
        btn1.setOnClickListener(v -> addChar('1'));
        btn2.setOnClickListener(v -> addChar('2'));
        btn3.setOnClickListener(v -> addChar('3'));
        btn4.setOnClickListener(v -> addChar('4'));
        btn5.setOnClickListener(v -> addChar('5'));
        btn6.setOnClickListener(v -> addChar('6'));
        btn7.setOnClickListener(v -> addChar('7'));
        btn8.setOnClickListener(v -> addChar('8'));
        btn9.setOnClickListener(v -> addChar('9'));

        btnSup.setOnClickListener(v -> removeChar());
        btnClear.setOnClickListener(v -> clearDisplay());
        btnRes.setOnClickListener(v -> calculate());

        btnDot.setOnClickListener(v -> addDecimal());
        btnPar1.setOnClickListener(v-> addOperation('('));
        btnPar2.setOnClickListener(v-> addOperation(')'));

        btnAdd.setOnClickListener(v -> addOperation('+'));
        btnMul.setOnClickListener(v -> addOperation('*'));
        btnDiv.setOnClickListener(v -> addOperation('/'));
        btnSub.setOnClickListener(v -> addOperation('-'));

    }

    private void setVariables() {
        expression = new StringBuilder();
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
    }

    private void addChar(char c) {
        expression.append(c);
        updateDisplay();
    }

    private void addDecimal() {
        if (!isDecimal) {
            expression.append(".");
            isDecimal = true;
            updateDisplay();
        }
    }

    private void addOperation(char c) {
        String operation = "+-*/";
        if (expression.length() == 0 && c != '(') {
            return;
        }
        if (operation.contains(expression.substring(expression.length() -1)) && c != '(') {
            return;
        }
        expression.append(c);
        isDecimal = false;
        updateDisplay();
    }

    private void removeChar() {
        if (expression.length() > 0) {
            if (expression.charAt(expression.length() - 1) == '.') {
                isDecimal = false;
            }
            expression.deleteCharAt(expression.length() - 1);
            updateDisplay();
        }
    }

    private void calculate() {
        if(expression.charAt(expression.length() - 1) == '.') {
            return;
        }
        try {
            // Convert StringBuilder to String before passing it to ExpressionBuilder
            Expression exp = new ExpressionBuilder(expression.toString()).build();
            double result = exp.evaluate();
            sendResult(result);
        } catch (Exception e) {
            // Handle invalid expressions gracefully
            expression = new StringBuilder("Error");
            updateDisplay();
        }
    }

    private void sendResult(Double number) {
        // Round result at 5 digits
        number = Math.round(number * 100000.0) / 100000.0;
        String resultString;
        if (number % 1 == 0) {
            resultString = String.valueOf(number.intValue());
        } else {
            resultString = String.valueOf(number);
            isDecimal = true;
        }
        clearDisplay();
        expression = new StringBuilder(resultString);
        updateDisplay();
    }

    private void updateDisplay() {
        // Display the numbers in the screen
        ((TextView) findViewById(R.id.txtDisplay)).setText(expression.toString());
        ((TextView) findViewById(R.id.txtRes)).setText(temp.toString());
    }

    private void clearDisplay() {
        expression = new StringBuilder();
        temp = new StringBuilder();
        isDecimal = false;
        updateDisplay();
    }
}




/*
    private void addOperation(String operation) {
        if(temp.length() > 0 && expression.length() > 0) {
            calculate();
            return;
        }
        if(temp.length() == 0 && expression.length() == 0) {
            return;
        }
        if(expression.length() == 0) {
            return;
        }
        isDecimal = false;
        temp = new StringBuilder(expression + operation);
        expression = new StringBuilder();
        updateDisplay();
    }

    private void addParenthesis(String parenthesis) {
        expression.append(parenthesis);
        updateDisplay();
    }

    private void calculate() {
        if(temp.length() == 0 || expression.length() == 0) {
            return;
        }
        // Calculate the result
        char operation = temp.charAt(temp.length() - 1);
        double firstPart = Double.parseDouble(temp.substring(0, temp.length() - 1));
        double secondPart = Double.parseDouble(expression.toString());
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


     */