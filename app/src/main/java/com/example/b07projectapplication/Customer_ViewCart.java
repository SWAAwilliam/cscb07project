package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Customer_ViewCart extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    CartAdapter adapter;
    ArrayList<Product> fullCart;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_cart);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        final ArrayList<Integer> pIndex = bundle.getIntegerArrayList("BUNDLE");
        final ArrayList<Integer> pQuantity = bundle.getIntegerArrayList("BUNDLE2");
        String id = bundle.getString("userid");
        recyclerView = findViewById(R.id.cart_view);
        ref = FirebaseDatabase.getInstance().getReference("users").child(id).child("products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fullCart = new ArrayList<>();
        adapter = new CartAdapter(this,fullCart);
        recyclerView.setAdapter(adapter);
        total = findViewById(R.id.cart_total);


        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                double carttotal = 0;
                if (task.isSuccessful()){

                    for (DataSnapshot data: task.getResult().getChildren()){
                        String s = data.getKey();
                        if (pIndex.contains(Integer.valueOf(s))){
                            Product p = data.getValue(Product.class);
                            int j = pIndex.indexOf(Integer.valueOf(s));
                            p.setQuantity(pQuantity.get(j));
                            if(p.getQuantity() > 0){
                            fullCart.add(p);
                            carttotal += p.getQuantity() * p.getPrice();}
                        }
                    }
                    adapter.notifyDataSetChanged();

                }
                total.setText("Your total is $"+String.valueOf(carttotal));
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent back = new Intent(Customer_ViewCart.this, Customer_ViewMyStores.class);
        startActivity(back);
        finish();
    }
}