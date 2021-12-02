package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Customer_ViewProducts extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    ProductAdapter adapter;
    ArrayList<Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_products);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        //get the userId of the clicked store
        String id = bundle.getString("userid");
        //System.out.println("asede: " + id);


        recyclerView = findViewById(R.id.product_view);
        ref = FirebaseDatabase.getInstance().getReference("users").child(id).child("products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ProductAdapter(this, list);
        recyclerView.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Product p = data.getValue(Product.class);
                    //System.out.println(p.getName());
                    //System.out.println(p.getPriceString());
                    list.add(p);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent back = new Intent(Customer_ViewProducts.this, Customer_ViewMyStores.class);
        startActivity(back);
        finish();
    }
}