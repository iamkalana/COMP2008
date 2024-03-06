package com.example.food_app_v03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodVH> {
    private ArrayList<Food> foodList;
    private ItemClick itemClick;

    public FoodAdapter(ArrayList<Food> foodList, ItemClick itemClick) {
        this.foodList = foodList;
        this.itemClick = itemClick;
    }

    public class FoodVH extends RecyclerView.ViewHolder {

        private ImageView food_imageV;
        private TextView food_nameV, foodPriceV;

        public FoodVH(@NonNull View itemView, ItemClick itemClick) {
            super(itemView);
            food_imageV = itemView.findViewById(R.id.food_image);
            food_nameV = itemView.findViewById(R.id.food_name);
            foodPriceV = itemView.findViewById(R.id.food_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClick != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            itemClick.clickedItem(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public FoodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_item, parent, false);
        FoodVH foodVH = new FoodVH(view, itemClick);
        return foodVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodVH holder, int position) {
        String foodName = foodList.get(position).getFood_name();
        String unitPrice = foodList.get(position).getFood_price();
        holder.food_nameV.setText(foodName);
        holder.foodPriceV.setText("LKR " + unitPrice);
        holder.food_imageV.setImageResource(foodList.get(position).food_imagePath);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

}
