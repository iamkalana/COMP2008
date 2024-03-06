package com.example.island_builder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    static boolean remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager frag = getSupportFragmentManager();

        MapData mapData = MapData.get();
        MapFragment map = (MapFragment) frag.findFragmentById(R.id.map_frame);

        StructureData structureData = StructureData.get();
        SelectorFragment selector = (SelectorFragment) frag.findFragmentById(R.id.selector_frame);

        if (map == null) {
            map = new MapFragment(mapData);
            frag.beginTransaction().add(R.id.map_frame, map).commit();
        }
        if (selector == null) {
            selector = new SelectorFragment(structureData);
            frag.beginTransaction().add(R.id.selector_frame, selector).commit();
        }

        FloatingActionButton regenBtn = findViewById(R.id.regenBtn);
        MapFragment finalMap = map;
        regenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapData.regenerate();
                finalMap.mapAdapter.notifyDataSetChanged();
            }
        });

        remove = false;

        FloatingActionButton dltBtn = findViewById(R.id.dltBtn);
        SelectorFragment finalSelector = selector;
        dltBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                remove = true;
                finalSelector.selectorAdapter.notifyDataSetChanged();
            }
        });



        FloatingActionButton editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditSelector.class);
                startActivity(i);
            }
        });

    }
}