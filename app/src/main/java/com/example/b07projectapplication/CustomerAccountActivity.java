package com.example.b07projectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerAccountActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account_acitivity);
        button = findViewById(R.id.ca_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCAccount();
            }
        });

    }

    public void createCAccount() {
        Intent intent = new Intent(this, CustomerAccountActivity.class);
        startActivity(intent);

    }
}
