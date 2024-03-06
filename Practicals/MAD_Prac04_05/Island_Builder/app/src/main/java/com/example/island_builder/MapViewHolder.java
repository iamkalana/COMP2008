package com.example.island_builder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    Events events;

    public MapViewHolder(@NonNull View itemView, Events events) {
        super(itemView);
        imageView1 = itemView.findViewById(R.id.mapCell1);
        imageView2 = itemView.findViewById(R.id.mapCell2);
        imageView3 = itemView.findViewById(R.id.mapCell3);
        imageView4 = itemView.findViewById(R.id.mapCell4);
        imageView5 = itemView.findViewById(R.id.mapCell5);
        this.events = events;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        events.pickItem(getAdapterPosition(), view);
    }
}
