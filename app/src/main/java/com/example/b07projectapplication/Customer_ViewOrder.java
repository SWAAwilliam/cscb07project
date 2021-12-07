package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Customer_ViewOrder extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    OrderAdapterCustomer adapter;
    ArrayList<Order> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_view_orders);
        getSupportActionBar().hide();
        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView = findViewById(R.id.orders_view);
        ref = FirebaseDatabase.getInstance().getReference("users").child(userUID).child("orders");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new OrderAdapterCustomer(this, list);
        recyclerView.setAdapter(adapter);

        adapter.setButtonClickListener(new OrderAdapterCustomer.ButtonClickListener() {
            @Override
            public void onClick(int position) {
                completeOrder(list.get(position));
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Order o = data.getValue(Order.class);
                    list.add(o);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    private void completeOrder(Order o){
        list.remove(o);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(Customer_ViewOrder.this, CustomerHomePage.class);
        startActivity(back);
        finish();
    }
}