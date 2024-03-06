package com.example.food_app_v03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeVH> {

    private ArrayList<Food> homeFoodList;
    private ItemClick itemClick;

    public HomeAdapter(ArrayList<Food> homeFoodList, ItemClick itemClick) {
        this.homeFoodList = homeFoodList;
        this.itemClick = itemClick;
    }

    public class HomeVH extends RecyclerView.ViewHolder {

        private ImageView homeItemIV;
        private TextView homeItem_name, homeItem_price;

        public HomeVH(@NonNull View itemView, ItemClick itemClick) {
            super(itemView);
            homeItemIV = itemView.findViewById(R.id.home_imageView);
            homeItem_name = itemView.findViewById(R.id.home_item_name);
            homeItem_price = itemView.findViewById(R.id.home_item_price);
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
    public HomeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_menu_item, parent, false);
        HomeVH homeVH = new HomeVH(view, itemClick);

        return homeVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVH holder, int position) {
        holder.homeItemIV.setImageResource(homeFoodList.get(position).getFood_imagePath());
        holder.homeItem_name.setText(homeFoodList.get(position).getFood_name());
        holder.homeItem_price.setText("LKR " + homeFoodList.get(position).getFood_price());
    }

    @Override
    public int getItemCount() {
        return homeFoodList.size();
    }
}
