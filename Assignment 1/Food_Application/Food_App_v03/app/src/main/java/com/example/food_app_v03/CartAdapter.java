package com.example.food_app_v03;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartVH> {

    private ArrayList<Order> orders;
    private DBModel dbModel = new DBModel();

    public CartAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public class CartVH extends RecyclerView.ViewHolder {

        private TextView cart_item_nameV, item_amount, total_price;
        private Button removeBtn, addBtn, minusBtn;
        private ImageView imageView;

        public CartVH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_imageView);
            cart_item_nameV = itemView.findViewById(R.id.cart_item_name);
            item_amount = itemView.findViewById(R.id.item_amount);
            total_price = itemView.findViewById(R.id.total_price);
            removeBtn = itemView.findViewById(R.id.removeBtn);
            addBtn = itemView.findViewById(R.id.cart_plus);
            minusBtn = itemView.findViewById(R.id.cart_minus);
            dbModel.load(itemView.getContext());
        }
    }

    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_item, parent, false);
        CartVH cartVH = new CartVH(view);
        return cartVH;
    }

    @Override
    public void onBindViewHolder(@NonNull CartVH holder, @SuppressLint("RecyclerView") int position) {
        holder.cart_item_nameV.setText(dbModel.getFoodByID(orders.get(position).getItem()).getFood_name());
        holder.imageView.setImageResource(dbModel.getFoodByID(orders.get(position).getItem()).getFood_imagePath());
        holder.item_amount.setText(String.valueOf(orders.get(position).getAmount()));
        holder.total_price.setText(String.valueOf(String.format("%.2f", orders.get(position).getTotalPrice())));
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amt = orders.get(position).getAmount();
                amt++;
                orders.get(position).setAmount(amt);
                orders.get(position).setTotalPrice(orders.get(position).getUnitPrice() * orders.get(position).getAmount());
                holder.total_price.setText(String.valueOf(String.format("%.2f", orders.get(position).getTotalPrice())));
                holder.item_amount.setText(String.valueOf(orders.get(position).getAmount()));
                CartFragment.getTotalCO();
            }
        });
        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orders.get(position).getAmount() > 1) {
                    int amt = orders.get(position).getAmount();
                    amt--;
                    orders.get(position).setAmount(amt);
                    orders.get(position).setTotalPrice(orders.get(position).getUnitPrice() * orders.get(position).getAmount());
                    holder.total_price.setText(String.valueOf(String.format("%.2f", orders.get(position).getTotalPrice())));
                    holder.item_amount.setText(String.valueOf(orders.get(position).getAmount()));
                    CartFragment.getTotalCO();
                }
            }
        });
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orders.remove(position);
                CartFragment.getTotalCO();
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
