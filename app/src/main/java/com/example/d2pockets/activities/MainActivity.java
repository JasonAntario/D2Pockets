package com.example.d2pockets.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.d2pockets.helpers.ConnectionHelper;
import com.example.d2pockets.constants.Endpoints;
import com.example.d2pockets.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private TextView tvResponse;
    private Endpoints endpoints = new Endpoints();
    private String apiKey = "a81f82870a4a4b0aa302632f91768e6a";
    private ConnectionHelper conHelper = new ConnectionHelper();
    private ProgressBar pbConnection;
    private TextInputLayout tilUsername;
    private TextInputEditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        pbConnection = findViewById(R.id.pbConnection);
        tvResponse = findViewById(R.id.tvResponse);
        etUsername = findViewById(R.id.etLogin);
        tilUsername = findViewById(R.id.tilLogin);

    }

    public void signIn(View view) throws IOException {
        if (etUsername.getText().toString().isEmpty()) {
            tilUsername.setError(getString(R.string.error_empty_filed));
        } else {
            sendRequest();
            //need waiting d2 api request
            Intent intent = new Intent(this, InventoryActivity.class);
            startActivity(intent);
        }
    }

    private void sendRequest() {
        pbConnection.setVisibility(View.VISIBLE);
        new Thread(() -> {
            try {
                conHelper.getCharachterIDs(etUsername.getText().toString());
                conHelper.getCharacterInfo(0);
                conHelper.getItemPerks(2);
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