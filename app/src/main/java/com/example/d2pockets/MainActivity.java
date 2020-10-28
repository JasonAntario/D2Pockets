package com.example.d2pockets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private TextView tvResponse;
    private String apiKey = "a81f82870a4a4b0aa302632f91768e6a";
    private String URL = "https://www.bungie.net/Platform/Destiny2/SearchDestinyPlayer/-1/jasonantario/";
    private RecyclerView rvInventory;

    private List<String> weaponNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvResponse = findViewById(R.id.tvResponse);
        weaponNames = Arrays.asList(getResources().getStringArray(R.array.weapon_names));
        rvInventory = findViewById(R.id.inventory_recycler_view);

        InvAdapter invAdapter = new InvAdapter(this, weaponNames);
        rvInventory.setAdapter(invAdapter);
        rvInventory.setLayoutManager(new LinearLayoutManager(this));
    }

    public void signIn(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url(URL).header("X-API-KEY", apiKey).build();
//                    Response response = null;
//                    response = client.newCall(request).execute();
//                    Log.e("!@#", response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }}).start();

    }
}