package com.example.b07projectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerAccountActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account_acitivity);
        getSupportActionBar().hide();
    }

    public void createCAccount() {
        //Change the purpose of this button to creating an account when clicked
        Intent intent = new Intent(this, CustomerAccountActivity.class);
        Button button = (Button) findViewById(R.id.c_account);
        startActivity(intent);

    }
}
