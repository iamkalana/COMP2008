package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button metricBtn, imperialBtn;
    String unit, wSymbol, hSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        metricBtn = findViewById(R.id.metric_btn);
        imperialBtn = findViewById(R.id.imperial_btn);

        metricBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unit = "metric";
                wSymbol = "KG";
                hSymbol = "CM";
                passUnit();
            }
        });

        imperialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unit = "imperial";
                wSymbol = "lb";
                hSymbol = "Inch";
                passUnit();
            }
        });

    }

    private void passUnit() {
        Intent i = new Intent(this, ThirdActivity.class);
        i.putExtra("UNIT", unit);
        i.putExtra("W_SYMBOL", wSymbol);
        i.putExtra("H_SYMBOL", hSymbol);
        startActivity(i);
    }
}