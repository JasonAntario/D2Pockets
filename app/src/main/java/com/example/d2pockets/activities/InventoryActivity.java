package com.example.d2pockets.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d2pockets.adapters.WeaponAdapter;
import com.example.d2pockets.R;

import java.util.Arrays;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private RecyclerView rvInventory;
    private List<String> weaponNames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);

        weaponNames = Arrays.asList(getResources().getStringArray(R.array.weapon_names));
        rvInventory = findViewById(R.id.inventory_recycler_view);

        WeaponAdapter weaponAdapter = new WeaponAdapter(this, weaponNames);
        rvInventory.setAdapter(weaponAdapter);
        rvInventory.setLayoutManager(new LinearLayoutManager(this));

    }
}
