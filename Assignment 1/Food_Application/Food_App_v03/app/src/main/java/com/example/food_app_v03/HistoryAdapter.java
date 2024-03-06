package com.example.food_app_v03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> {

    private ArrayList<History> histories;
    private DBModel dbModel = new DBModel();

    public HistoryAdapter(ArrayList<History> histories) {
        this.histories = histories;
    }

    public class HistoryVH extends RecyclerView.ViewHolder {

        TextView date, orderID, itemName, restaurant, unitPrice, amount, totalPrice;

        public HistoryVH(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.h_date);
            orderID = itemView.findViewById(R.id.h_orderID);
            itemName = itemView.findViewById(R.id.h_orderItem);
            restaurant = itemView.findViewById(R.id.h_restaurant);
            unitPrice = itemView.findViewById(R.id.h_unitPrice);
            amount = itemView.findViewById(R.id.h_amount);
            totalPrice = itemView.findViewById(R.id.h_total);
            dbModel.load(itemView.getContext());
        }
    }

    @NonNull
    @Override
    public HistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.history_item, parent, false);
        HistoryVH historyVH = new HistoryVH(view);
        return historyVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {
        holder.date.setText(histories.get(position).getDate());
        holder.orderID.setText(histories.get(position).getOrderID());
        holder.itemName.setText(dbModel.getFoodByID(histories.get(position).getItem()).getFood_name());
        holder.restaurant.setText(dbModel.getRestaurantByID(histories.get(position).getRestaurant()).getR_name());
        holder.unitPrice.setText(String.format("%.2f", Double.parseDouble(histories.get(position).getUnitPrice())));
        holder.amount.setText(histories.get(position).getAmount());
        holder.totalPrice.setText(String.format("%.2f", Double.parseDouble(histories.get(position).getTotalPrice())));
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

}
