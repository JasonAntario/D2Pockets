package com.example.d2pockets;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        InvAdapter invAdapter = new InvAdapter(this, weaponNames);
        rvInventory.setAdapter(invAdapter);
        rvInventory.setLayoutManager(new LinearLayoutManager(this));

    }
}
