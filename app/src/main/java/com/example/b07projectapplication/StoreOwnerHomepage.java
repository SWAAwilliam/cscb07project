package com.example.b07projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StoreOwnerHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_homepage);
        getSupportActionBar().hide();
    }
}