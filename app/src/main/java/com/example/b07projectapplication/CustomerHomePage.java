package com.example.b07projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustomerHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_homepage);
        getSupportActionBar().hide();
    }


    public void sendToViewStores(View view){
        Intent intent = new Intent(CustomerHomePage.this, Customer_ViewMyStores.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        ImageButton button = (ImageButton) findViewById(R.id.btnViewStoresCustomer);
        startActivity(intent);
    }


}
