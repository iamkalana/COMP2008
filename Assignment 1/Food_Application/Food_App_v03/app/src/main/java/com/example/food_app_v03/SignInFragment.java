package com.example.food_app_v03;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_app_v03.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {
    View view;
    private User newUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        Button signInBtn = view.findViewById(R.id.signInBtn);
        Button registerBtn = view.findViewById(R.id.regBtn);
        EditText email = view.findViewById(R.id.signEmail);
        EditText password = view.findViewById(R.id.signPass);

        DBModel dbModel = new DBModel();
        dbModel.load(getContext());

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUser = dbModel.getLoggedUser(email.getText().toString(), password.getText().toString());
                MainActivity.loggedUser = newUser;
                if (newUser != null) {

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.mainFragCont, new DashboardFragment()).commit();

                } else {
                    Toast.makeText(getContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}