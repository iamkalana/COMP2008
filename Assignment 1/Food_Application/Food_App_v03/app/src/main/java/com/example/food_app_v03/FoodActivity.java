package com.example.food_app_v03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class FoodActivity extends AppCompatActivity {

    static String restaurant;

    public FoodActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Food_App_v03);
        setContentView(R.layout.activity_food);

        Intent preIntent = getIntent();
        restaurant = preIntent.getStringExtra("RES");

    }
}