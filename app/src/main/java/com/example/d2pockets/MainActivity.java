package com.example.d2pockets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private TextView tvResponse;
    private Endpoints endpoints = new Endpoints();
    private String apiKey = "a81f82870a4a4b0aa302632f91768e6a";
    private ConnectionHelper conHelper = new ConnectionHelper();
    private ProgressBar pbConnection;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        pbConnection = findViewById(R.id.pbConnection);
        tvResponse = findViewById(R.id.tvResponse);
    }

    public void signIn(View view) {
        pbConnection.setVisibility(View.VISIBLE);
        new Thread(() -> {
            try {
                conHelper.getCharachterIDs("jasonantario");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}