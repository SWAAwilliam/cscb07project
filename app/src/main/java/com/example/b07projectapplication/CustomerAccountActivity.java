package com.example.b07projectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerAccountActivity extends AppCompatActivity {

    private EditText input_email;
    private EditText input_password;
    private EditText input_fullname;
    private Button btn_create;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_account_activity);
        getSupportActionBar().hide();

        input_email = findViewById(R.id.editEmail);
        input_password = findViewById(R.id.editPassword);
        input_fullname = findViewById(R.id.editFullName);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        btn_create = findViewById(R.id.c_account);

        btn_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                authentication();

            }
        });
    }

    /*public void createCAccount() {
        //Change the purpose of this button to creating an account when clicked
        Intent intent = new Intent(this, CustomerAccountActivity.class);
        Button button = (Button) findViewById(R.id.c_account);
        startActivity(intent);

    }

     */
    private void authentication(){
        String email = input_email.getText().toString();
        String fullname = input_fullname.getText().toString();
        String password = input_password.getText().toString();

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sendUser();
                    Toast.makeText(CustomerAccountActivity.this, "Created New Account Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    private void sendUser(){
        Intent intent = new Intent(CustomerAccountActivity.this, NewAccountActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
