package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FourthActivity extends AppCompatActivity {

    TextView weightDisplay, heightDisplay, bmiLabel, bmiDisplay, description;
    Button plusButton, minusButton;
    String mainUnit, wUnit, hUnit;
    double weight, height, bmiVal;
    DecimalFormat twoDec = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        weightDisplay = findViewById(R.id.w_display);
        heightDisplay = findViewById(R.id.h_display);
        bmiLabel = findViewById(R.id.bmi_lbl);
        bmiDisplay = findViewById(R.id.bmi_display);
        description = findViewById(R.id.desc_display);
        plusButton = findViewById(R.id.zoom_in);
        minusButton = findViewById(R.id.zoom_out);

        Intent preIntent = getIntent();
        mainUnit = preIntent.getStringExtra("MAIN_UNIT");
        wUnit = preIntent.getStringExtra("W_UNIT");
        hUnit = preIntent.getStringExtra("H_UNIT");
        weight = preIntent.getDoubleExtra("WEIGHT", 0.00);
        height = preIntent.getDoubleExtra("HEIGHT", 0.00);

        weightDisplay.setText("Your weight is " + weight + " " + wUnit);
        heightDisplay.setText("Your height is " + height + " " + hUnit);
        bmiVal = calcBMI();
        bmiDisplay.setText(String.valueOf(bmiVal));
        genDescription();
        Log.d("BMI (Display value)", String.valueOf(bmiVal));

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_fourth, null);
        String tag = (String) view.getTag();
        Log.d("Layout tag", tag);

        if (tag.equals("tablet_mode")) {
            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    zoomIn();
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    zoomOut();
                }
            });

        }
    }

    private double calcBMI() {
        double BMI;
        if (mainUnit.equals("metric")) {
            height = height / 100;
            BMI = weight / (height * height);
        } else {
            BMI = weight / (height * height) * 703;
        }
        BMI = Double.parseDouble(twoDec.format(BMI));
        Log.d("BMI (Original value)", String.valueOf(BMI));
        return BMI;
    }

    private void genDescription() {
        if (bmiVal < 18.5) {
            description.setText("Underweight");
            description.setBackgroundColor(getResources().getColor(R.color.light_blue));
        } else if (bmiVal < 25) {
            description.setText("Healthy weight");
            description.setBackgroundColor(getResources().getColor(R.color.light_green));
        } else if (bmiVal < 30) {
            description.setText("Overweight\nbut not obese");
            description.setBackgroundColor(getResources().getColor(R.color.light_yellow));
        } else if (bmiVal < 35) {
            description.setText("Obese class\nI");
            description.setBackgroundColor(getResources().getColor(R.color.light_orange));
        } else if (bmiVal < 40) {
            description.setText("Obese class\nII");
            description.setBackgroundColor(getResources().getColor(R.color.light_red));
        } else {
            description.setText("Obese class\nIII");
            description.setBackgroundColor(getResources().getColor(R.color.dark_red));
        }
    }

    private void zoomIn() {
        weightDisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, weightDisplay.getTextSize() * 1.1F);
        heightDisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightDisplay.getTextSize() * 1.1F);
        bmiLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiLabel.getTextSize() * 1.1F);
        bmiDisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiDisplay.getTextSize() * 1.1F);
        description.setTextSize(TypedValue.COMPLEX_UNIT_PX, description.getTextSize() * 1.1F);
    }

    private void zoomOut() {
        weightDisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, weightDisplay.getTextSize() * 0.9F);
        heightDisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightDisplay.getTextSize() * 0.9F);
        bmiLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiLabel.getTextSize() * 0.9F);
        bmiDisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiDisplay.getTextSize() * 0.9F);
        description.setTextSize(TypedValue.COMPLEX_UNIT_PX, description.getTextSize() * 0.9F);
    }

}