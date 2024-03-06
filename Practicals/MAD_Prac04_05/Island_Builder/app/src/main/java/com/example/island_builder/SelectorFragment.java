package com.example.island_builder;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class SelectorFragment extends Fragment implements Events {

    StructureData data;
    static Structure structure;
    SelectorAdapter selectorAdapter;
    static int selectedPos;

    public SelectorFragment() {
        // Required empty public constructor
    }

    public SelectorFragment(StructureData data) {
        this.data = data;
        selectedPos = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selector, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.selector_recycle);
        selectorAdapter = new SelectorAdapter(data, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false));

        recyclerView.setAdapter(selectorAdapter);
        return view;
    }

    @Override
    public void pickItem(int position, View view) {
        structure = data.get(position);

        int prevPos = selectedPos;
        selectedPos = position;
        MainActivity.remove = false;

        selectorAdapter.notifyItemChanged(prevPos);
        selectorAdapter.notifyItemChanged(position);

        Log.d("pickItem SF: ", data.get(position).getLabel());
    }

    static Structure passStructure() {
        return structure;
    }
    static int selected(){
        return selectedPos;
    }
}