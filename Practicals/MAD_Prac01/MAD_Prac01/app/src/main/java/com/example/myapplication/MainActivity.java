package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText firstVal;
    EditText secondVal;
    Button plus;
    Button minus;
    Button divide;
    Button multiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus = findViewById(R.id.plusBtn);
        minus = findViewById(R.id.minusBtn);
        divide = findViewById(R.id.divisionBtn);
        multiply = findViewById(R.id.multiplyBtn);
        result = findViewById(R.id.answer);
        firstVal = findViewById(R.id.first_value);
        secondVal = findViewById(R.id.second_value);

        // Callback method for addition button
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!firstVal.getText().toString().isEmpty() && !secondVal.getText().toString().isEmpty()) {
                    double x = Double.parseDouble(String.valueOf(firstVal.getText()));
                    double y = Double.parseDouble(String.valueOf(secondVal.getText()));

                    double sum = x + y;

                    result.setText(String.valueOf(sum));
                } else {
                    result.setText("Input values cannot be empty!");
                }

            }
        });

        // Callback method for subtraction button
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!firstVal.getText().toString().isEmpty() && !secondVal.getText().toString().isEmpty()) {
                    double x = Double.parseDouble(String.valueOf(firstVal.getText()));
                    double y = Double.parseDouble(String.valueOf(secondVal.getText()));

                    double sub = x - y;

                    result.setText(String.valueOf(sub));
                } else {
                    result.setText("Input values cannot be empty!");
                }

            }
        });

        // Callback method for multiplication button
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!firstVal.getText().toString().isEmpty() && !secondVal.getText().toString().isEmpty()) {
                    double x = Double.parseDouble(String.valueOf(firstVal.getText()));
                    double y = Double.parseDouble(String.valueOf(secondVal.getText()));

                    double multi = x * y;

                    result.setText(String.valueOf(multi));
                } else {
                    result.setText("Input values cannot be empty!");
                }
            }
        });

        // Callback method for division button
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!firstVal.getText().toString().isEmpty() && !secondVal.getText().toString().isEmpty()) {
                    double x = Double.parseDouble(String.valueOf(firstVal.getText()));
                    double y = Double.parseDouble(String.valueOf(secondVal.getText()));

                    if (y != 0) {
                        double dvd = x / y;
                        result.setText(String.valueOf(dvd));
                    } else {
                        result.setText("Divide by zero!");
                    }
                } else {
                    result.setText("Input values cannot be empty!");
                }
            }
        });


    }
}