package com.example.assignment2_parta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2_parta.databinding.FragmentUserDetailsBinding;

public class UserDetailsFragment extends Fragment {

    FragmentUserDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false);

        User user = UserDetailsFragmentArgs.fromBundle(getArguments()).getUser();

        binding.idTv.setText(String.valueOf(user.getId()));
        binding.nameTv.setText(user.getName());
        binding.usernameTv.setText(user.getUserName());
        binding.emailTv.setText(user.getEmail());
        binding.addressTv.setText(user.getAddress());
        binding.phoneTv.setText(user.getPhone());
        binding.webTv.setText(user.getWebsite());
        binding.companyTv.setText(user.getCompany());

        binding.viewPostBtn.setOnClickListener(view -> {

            NavController navController = Navigation.findNavController(view);
            UserDetailsFragmentDirections.ActionUserDetailsFragmentToUserPostsFragment dire =
                    UserDetailsFragmentDirections.actionUserDetailsFragmentToUserPostsFragment(user.getId());
            navController.navigate(dire);

        });

        return binding.getRoot();
    }
}