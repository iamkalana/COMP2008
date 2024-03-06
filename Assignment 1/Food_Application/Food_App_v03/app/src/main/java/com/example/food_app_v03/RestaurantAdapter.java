package com.example.food_app_v03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantVH> {
    private ArrayList<Restaurant> restaurantList;
    private ItemClick itemClick;

    public RestaurantAdapter(ArrayList<Restaurant> restaurantList, ItemClick itemClick) {
        this.restaurantList = restaurantList;
        this.itemClick = itemClick;
    }

    public class RestaurantVH extends RecyclerView.ViewHolder {

        ImageView res_imageV;
        TextView res_nameV, res_addressV;

        public RestaurantVH(@NonNull View itemView, ItemClick itemClick) {
            super(itemView);
            res_imageV = itemView.findViewById(R.id.res_image);
            res_nameV = itemView.findViewById(R.id.res_name);
            res_addressV = itemView.findViewById(R.id.res_address);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClick != null){
                        if(getAdapterPosition() != RecyclerView.NO_POSITION){
                            itemClick.clickedItem(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public RestaurantVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.res_item,parent,false);
        RestaurantVH restaurantVH = new RestaurantVH(view, itemClick);
        return restaurantVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantVH holder, int position) {
        holder.res_nameV.setText(restaurantList.get(position).getR_name());
        holder.res_imageV.setImageResource(restaurantList.get(position).getR_imagePath());
        holder.res_addressV.setText(restaurantList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
