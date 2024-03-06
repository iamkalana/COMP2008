package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class ThirdActivity extends AppCompatActivity {

    TextView header, wSymbol, hSymbol;
    Button nextButton;
    EditText w_textInput, h_textInput;
    Slider wSlider, hSlider;
    String currentUnit, weightSymbol, heightSymbol;
    double weight, height, wRange, hRange;
    DecimalFormat threeDec = new DecimalFormat("#.###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        header = findViewById(R.id.heading_textView);
        wSymbol = findViewById(R.id.w_unit);
        hSymbol = findViewById(R.id.h_unit);
        nextButton = findViewById(R.id.next_btn);
        w_textInput = findViewById(R.id.w_val);
        h_textInput = findViewById(R.id.h_val);
        wSlider = findViewById(R.id.weight_slider);
        hSlider = findViewById(R.id.height_slider);

        Intent preIntent = getIntent();
        currentUnit = preIntent.getStringExtra("UNIT");
        weightSymbol = preIntent.getStringExtra("W_SYMBOL");
        heightSymbol = preIntent.getStringExtra("H_SYMBOL");
        header.setText("You have selected\n" + currentUnit + " system");
        wSymbol.setText(weightSymbol);
        hSymbol.setText(heightSymbol);

        weight = 0;
        height = 0;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weight != 0 && height != 0) {
                    passValues();
                } else {
                    Snackbar snackbar = Snackbar.make(view, "Invalid weight or height!", 5000);
                    snackbar.show();
                }
            }
        });

        if (currentUnit.equals("imperial")) {
            wRange = 660;
            hRange = 118;
        } else {
            wRange = 300;
            hRange = 300;
        }

        wSlider.setValueTo((float) wRange);
        wSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (wSlider.isPressed()) {
                    weight = Double.parseDouble(threeDec.format(value));
                    w_textInput.setText(String.valueOf(weight));
                }
            }
        });

        w_textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double temp = 0;
                if (!editable.toString().isEmpty()) {
                    temp = Double.parseDouble(String.valueOf(editable));
                }
                if (temp >= 0 && temp <= wRange) {
                    weight = Double.parseDouble(threeDec.format(temp));
                    wSlider.setValue((float) weight);
                } else {
                    w_textInput.selectAll();
                    w_textInput.setError("Weight range 0-" + Math.round(wRange) + weightSymbol);
                    weight = 0.0;
                }
            }
        });

        hSlider.setValueTo((float) hRange);
        hSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (hSlider.isPressed()) {
                    height = Double.parseDouble(threeDec.format(value));
                    h_textInput.setText(String.valueOf(height));
                }
            }
        });

        h_textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double temp = 0;
                if (!editable.toString().isEmpty()) {
                    temp = Double.parseDouble(String.valueOf(editable));
                }
                if (temp >= 0 && temp <= hRange) {
                    height = Double.parseDouble(threeDec.format(temp));
                    hSlider.setValue((float) height);
                } else {
                    h_textInput.selectAll();
                    h_textInput.setError("Height range 0-" + Math.round(hRange) + heightSymbol);
                    height = 0.0;
                }
            }
        });
    }

    private void passValues() {
        Intent i = new Intent(this, FourthActivity.class);
        i.putExtra("WEIGHT", weight);
        i.putExtra("HEIGHT", height);
        i.putExtra("MAIN_UNIT", currentUnit);
        i.putExtra("W_UNIT", weightSymbol);
        i.putExtra("H_UNIT", heightSymbol);
        startActivity(i);
    }

}