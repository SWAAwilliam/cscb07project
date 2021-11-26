package com.example.b07projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreOwnerHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_homepage);
        getSupportActionBar().hide();
    }

    public void openProductPage(View view){
        Intent intent = new Intent(this, StoreOwner_ViewProducts.class);
        Button button = (Button) findViewById(R.id.btnProducts);
        startActivity(intent);
    }
}