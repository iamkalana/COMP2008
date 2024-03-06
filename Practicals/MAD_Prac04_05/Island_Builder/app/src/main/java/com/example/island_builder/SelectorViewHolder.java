package com.example.island_builder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView;
    TextView textView;
    ConstraintLayout selection;
    Events events;

    public SelectorViewHolder(@NonNull View itemView, Events events) {
        super(itemView);
        imageView = itemView.findViewById(R.id.selector_icon);
        textView = itemView.findViewById(R.id.selector_title);
        selection = itemView.findViewById(R.id.selection);
        this.events = events;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        events.pickItem(getAdapterPosition(), view);
    }
}
