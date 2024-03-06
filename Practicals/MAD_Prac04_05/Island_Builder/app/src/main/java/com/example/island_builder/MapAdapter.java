package com.example.island_builder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MapAdapter extends RecyclerView.Adapter<MapViewHolder> {
    MapData mapData;
    Events events;

    public MapAdapter(MapData mapData, Events events) {
        this.mapData = mapData;
        this.events = events;
    }

    @NonNull
    @Override
    public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_cell, parent, false);
        MapViewHolder mapHolder = new MapViewHolder(view, events);

        int size = parent.getMeasuredHeight() / MapData.HEIGHT + 1;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = size;
        lp.height = size;

        return mapHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MapViewHolder holder, int position) {
        int row = position % MapData.HEIGHT;
        int col = position / MapData.HEIGHT;
        holder.imageView1.setImageResource(mapData.get(row, col).getNorthWest());
        holder.imageView2.setImageResource(mapData.get(row, col).getNorthEast());
        holder.imageView3.setImageResource(mapData.get(row, col).getSouthWest());
        holder.imageView4.setImageResource(mapData.get(row, col).getSouthEast());
        holder.imageView5.setImageResource(Color.TRANSPARENT);
        if (mapData.get(row, col).getStructure() != null && !MainActivity.remove) {
            holder.imageView5.setImageResource(mapData.get(row, col).getStructure().getDrawableId());
            Log.d("Map Adapter", "clicked on map");
        } else if (MainActivity.remove){
            holder.imageView5.setImageResource(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return MapData.HEIGHT * MapData.WIDTH;
    }
}
