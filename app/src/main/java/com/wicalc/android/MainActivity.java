package com.wicalc.android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private StringBuilder numbers;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btnAdd, btnSup, btnDot, btnMul, btnDiv, btnRes, btnPar1, btnPar2;

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
    }

    private void setVariables() {
        numbers = new StringBuilder();

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
        btnDot = findViewById(R.id.btnDot);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnRes = findViewById(R.id.btnRes);
        btnPar1 = findViewById(R.id.btnPar1);
        btnPar2 = findViewById(R.id.btnPar2);

        ((EditText) findViewById(R.id.txtDisplay)).setText("------");
    }

    private void addNumber(int number) {
        numbers.append(number);
        updateDisplay();
    }

    private void removeNumber() {
        if (numbers.length() > 0) {
            numbers.deleteCharAt(numbers.length() - 1);
            updateDisplay();
        }
    }

    private void updateDisplay() {
        // Display the numbers in the screen
        ((EditText) findViewById(R.id.txtDisplay)).setText(numbers.toString());
    }
}