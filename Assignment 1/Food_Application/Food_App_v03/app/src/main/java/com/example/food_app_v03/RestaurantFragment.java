package com.example.food_app_v03;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RestaurantFragment extends Fragment implements ItemClick {
    private ArrayList<Restaurant> restaurants;

    public RestaurantFragment() {
    }

    public RestaurantFragment(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DBModel dbModel = new DBModel();
        dbModel.load(getContext());
        restaurants = dbModel.getAllRestaurants();
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        RecyclerView rv = view.findViewById(R.id.res_rcv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        RestaurantAdapter adapter = new RestaurantAdapter(restaurants, this);
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void clickedItem(int position) {
        Intent i = new Intent(getContext(), FoodActivity.class);
        i.putExtra("RES", restaurants.get(position).getR_id());
        startActivity(i);
    }
}