package com.example.island_builder;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MapFragment extends Fragment implements Events {

    MapData data;
    MapAdapter mapAdapter;

    public MapFragment() {
        // Required empty public constructor
    }

    public MapFragment(MapData data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.map_recycle);
        mapAdapter = new MapAdapter(data, this);

        recyclerView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                MapData.HEIGHT,
                GridLayoutManager.HORIZONTAL,
                false));

        recyclerView.setAdapter(mapAdapter);
        return view;

    }

    @Override
    public void pickItem(int position, View view) {
        int row = position % MapData.HEIGHT;
        int col = position / MapData.HEIGHT;

        if (SelectorFragment.passStructure() != null && !MainActivity.remove) {
            if (data.get(row, col).isBuildable()) {
                data.get(row, col).setStructure(SelectorFragment.passStructure());
                mapAdapter.notifyItemChanged(position);
                Log.d("pickItem MF: ", data.get(row, col).getStructure().getLabel());
            } else {
//                Toast.makeText(getContext(), "Can't build there!", Toast.LENGTH_SHORT).show();
                Toasty.warning(getContext(), "Can't build there!", Toast.LENGTH_SHORT, true).show();
            }
        } else if (MainActivity.remove && data.get(row, col).getStructure() != null) {
            data.get(row, col).setStructure(null);
            mapAdapter.notifyItemChanged(position);
        } else {
//            Toast.makeText(getContext(), "Select an element First!", Toast.LENGTH_SHORT).show();
            Toasty.info(getContext(), "Select an element!", Toast.LENGTH_SHORT, true).show();
        }
    }
}