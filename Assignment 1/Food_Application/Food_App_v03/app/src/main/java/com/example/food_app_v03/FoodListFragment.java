package com.example.food_app_v03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodListFragment extends Fragment implements ItemClick {

    View view;
    static ArrayList<Food> foodList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_food_list, container, false);
        DBModel dbModel = new DBModel();
        dbModel.load(getContext());

        TextView restaurantName = view.findViewById(R.id.label_res_name);
        restaurantName.setText(dbModel.getRestaurantByID(FoodActivity.restaurant).getR_name());

        ImageView backBtn = view.findViewById(R.id.fList_back);
        backBtn.setOnClickListener(view1 -> {
            getActivity().finish();
        });

        foodList = dbModel.getRestaurantFoods(FoodActivity.restaurant);
        RecyclerView rv = view.findViewById(R.id.food_rcv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        FoodAdapter adapter = new FoodAdapter(foodList, this);
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void clickedItem(int position) {
        NavController navController = Navigation.findNavController(view);
        FoodListFragmentDirections.ActionFoodListFragmentToFoodItemFragment dire =
                FoodListFragmentDirections.actionFoodListFragmentToFoodItemFragment(foodList.get(position));
        navController.navigate(dire);
    }
}