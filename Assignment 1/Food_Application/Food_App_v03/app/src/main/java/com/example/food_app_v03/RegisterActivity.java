package com.example.food_app_v03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText name, email, address, phone, password;
    private Button signUpBtn, cancelBtn;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Food_App_v03);
        setContentView(R.layout.activity_register);

        DBModel dbModel = new DBModel();
        dbModel.load(getApplicationContext());

        name = findViewById(R.id.eTextUName);
        email = findViewById(R.id.eTextUEmail);
        address = findViewById(R.id.eTextUAddress);
        phone = findViewById(R.id.eTextUPhone);
        password = findViewById(R.id.eTextUPass);
        signUpBtn = findViewById(R.id.signupBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        backBtn = findViewById(R.id.reg_back);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = name.getText().toString();
                String uEmail = email.getText().toString();
                String uPassword = password.getText().toString();
                String uAddress = address.getText().toString();
                String uPhone = phone.getText().toString();
                if(!uName.isEmpty() && !uEmail.isEmpty() && !uPassword.isEmpty() && !uAddress.isEmpty() && !uPhone.isEmpty()){
                    User newUser = new User(uName, uEmail, uAddress, uPhone, uPassword);
                    dbModel.addUser(newUser);
                    Toast.makeText(RegisterActivity.this, "You have registered successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        backBtn.setOnClickListener(view -> {
            finish();
        });

    }
}