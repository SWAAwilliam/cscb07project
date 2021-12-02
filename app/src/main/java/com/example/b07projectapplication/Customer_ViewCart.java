package com.example.b07projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class Customer_ViewCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_cart);

        Bundle bundle = getIntent().getExtras();

        ArrayList<Product> p = (ArrayList<Product>) bundle.get("product");

        for (Product pro : p){
            System.out.println("p :" + pro.getName());
        }




    }
}