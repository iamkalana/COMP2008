package com.example.island_builder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditSelector extends AppCompatActivity {
    private SelectorAdapter selectorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_selector);
        FragmentManager frag = getSupportFragmentManager();

        StructureData structureData = StructureData.get();
        SelectorFragment selector = (SelectorFragment) frag.findFragmentById(R.id.editFrame);

        if (selector == null) {
            selector = new SelectorFragment(structureData);
            frag.beginTransaction().add(R.id.editFrame, selector).commit();
        }

        Button removeBtn = findViewById(R.id.rmvBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        Button okBtn = findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}