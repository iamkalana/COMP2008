package com.example.food_app_v03;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment implements ItemClick {
    private ArrayList<Food> homeFoodList;
    private ArrayList<Food> randomFoodList;
    private TextView welcomeMsg;
    private ImageSlider imageSlider;
    View view;
    private DBModel dbModel;
    private int amount = 1;

    public HomeFragment() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbModel = new DBModel();
        dbModel.load(getContext());
//        dbModel.addRestaurantList();
//        dbModel.addFoodList();
        homeFoodList = dbModel.getAllFoods();
        int range = homeFoodList.size();
        randomFoodList = new ArrayList<>();

        for (int i = 0; randomFoodList.size() < 10; i++) {
            int idx = getRandomValue(19);
            if (!randomFoodList.contains(homeFoodList.get(idx))) {
                randomFoodList.add(homeFoodList.get(idx));
            }
        }

        view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = view.findViewById(R.id.home_rcv);
        rv.setLayoutManager(new GridLayoutManager(
                getActivity(), 2,
                GridLayoutManager.VERTICAL,
                false));
        HomeAdapter adapter = new HomeAdapter(randomFoodList, this);
        rv.setAdapter(adapter);

        welcomeMsg = view.findViewById(R.id.welcome_msg);

        if (MainActivity.loggedUser != null) {
            welcomeMsg.setText("Hello " + MainActivity.loggedUser.getU_name());
        } else {
            welcomeMsg.setText("Hello");
        }

        imageSlider = view.findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideImages = new ArrayList<>();
        slideImages.add(new SlideModel(R.drawable.s1, ScaleTypes.CENTER_CROP));
        slideImages.add(new SlideModel(R.drawable.s2, ScaleTypes.CENTER_CROP));
        slideImages.add(new SlideModel(R.drawable.s3, ScaleTypes.CENTER_CROP));
        slideImages.add(new SlideModel(R.drawable.s4, ScaleTypes.CENTER_CROP));
        slideImages.add(new SlideModel(R.drawable.s5, ScaleTypes.CENTER_CROP));
        slideImages.add(new SlideModel(R.drawable.s6, ScaleTypes.CENTER_CROP));
        slideImages.add(new SlideModel(R.drawable.s7, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideImages, ScaleTypes.CENTER_CROP);

        return view;
    }

    @Override
    public void clickedItem(int position) {
        addItemFromHome(position);
    }

    public void addItemFromHome(int position) {
        TextView d_foodName, d_price, d_restaurant;
        Button d_plus, d_minus, d_addToCart;
        ImageView d_foodItemIV;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add2cart_dialog);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        d_foodName = dialog.findViewById(R.id.dialog_name);
        d_plus = dialog.findViewById(R.id.dialog_add_btn);
        d_minus = dialog.findViewById(R.id.dialog_minus_btn);
        d_addToCart = dialog.findViewById(R.id.dialog_add_to_cart_btn);
        d_price = dialog.findViewById(R.id.dialog_food_price);
        d_foodItemIV = dialog.findViewById(R.id.dialog_iv);
        d_restaurant = dialog.findViewById(R.id.dialog_resName);

        d_foodName.setText(randomFoodList.get(position).getFood_name());
        d_restaurant.setText("by " + dbModel.getRestaurantByID(randomFoodList.get(position).getRe_id()).getR_name());
        d_price.setText(String.valueOf(String.format("%.2f", Double.parseDouble(randomFoodList.get(position).getFood_price()) * amount)));
        d_foodItemIV.setImageResource(randomFoodList.get(position).food_imagePath);

        d_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount++;
                d_addToCart.setText("ADD TO CART\n" + String.valueOf(amount));
                d_price.setText(String.valueOf(String.format("%.2f", Double.parseDouble(randomFoodList.get(position).getFood_price()) * amount)));
            }
        });

        d_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount > 1) {
                    amount--;
                    d_addToCart.setText("ADD TO CART\n" + String.valueOf(amount));
                    d_price.setText(String.valueOf(String.format("%.2f", Double.parseDouble(randomFoodList.get(position).getFood_price()) * amount)));
                }
            }
        });

        d_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.orderList.add(new Order(randomFoodList.get(position).getFood_id(), randomFoodList.get(position).getRe_id(),
                        Double.parseDouble(randomFoodList.get(position).getFood_price()), amount,
                        Double.parseDouble(randomFoodList.get(position).getFood_price()) * amount));
                amount = 1;
                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public int getRandomValue(int range) {
        return new Random().nextInt(range);
    }
}