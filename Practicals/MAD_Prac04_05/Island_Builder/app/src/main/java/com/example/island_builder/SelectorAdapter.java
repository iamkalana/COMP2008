package com.example.island_builder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SelectorAdapter extends RecyclerView.Adapter<SelectorViewHolder> {
    StructureData selectorData;
    private Events events;
    static int selectedItem;

    public SelectorAdapter(StructureData selectorData, Events events) {
        this.selectorData = selectorData;
        this.events = events;
        selectedItem = -1;
    }

    @NonNull
    @Override
    public SelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_selection, parent, false);
        SelectorViewHolder selectorHolder = new SelectorViewHolder(view, events);
        return selectorHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SelectorViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(selectorData.get(position).getDrawableId());
        holder.textView.setText(selectorData.get(position).getLabel());
        holder.selection.setBackgroundColor(Color.TRANSPARENT);

        if(SelectorFragment.selected() == position && !MainActivity.remove){
            holder.selection.setBackgroundColor(Color.parseColor("#DEFBC2"));
        }
    }

    @Override
    public int getItemCount() {
        return selectorData.size();
    }
}
