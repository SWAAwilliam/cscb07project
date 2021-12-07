package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreOwner_ViewOrders extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    OrderAdapter adapter;
    ArrayList<Order> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_view_orders);
        getSupportActionBar().hide();
        
        recyclerView = findViewById(R.id.orders_view);
        //////// UNCOMMENT WHEN DATABASE SETUP IS DONE
        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference("users").child(userUID).child("orders");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new OrderAdapter(this, list);
        recyclerView.setAdapter(adapter);

        adapter.setButtonClickListener(new OrderAdapter.ButtonClickListener() {
            @Override
            public void onClick(int position) {
                completeOrder(list.get(position));
            }
        });

       // updateList();

        //////// UNCOMMENT WHEN DATABASE SETUP IS DONE
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Order o = data.getValue(Order.class);
                    //System.out.println(p.getName());
                    //System.out.println(p.getPriceString());
                    list.add(o);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

   /* private void updateList(){
        //////// UNCOMMENT WHEN DATABASE SETUP IS DONE
        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //FirebaseAuth.getInstance().getReference().child("users").child(userUID).child("ordered");
        ref = FirebaseDatabase.getInstance().getReference().child("users").child(userUID).child("orders");
        //FirebaseDatabase.getInstance().getReference().
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    list.clear();
                    for (DataSnapshot data: task.getResult().getChildren()){
                        Order o = data.getValue(Order.class);
                        list.add(o);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        /* for testing........ COMMENT WHEN DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        list.clear();
        Order o1 = new Order();
        o1.setCustomerName("T1");
        o1.setOwnerUID("t1");
        o1.setCustomerUID("t1");
        o1.setComplete(false);
        ArrayList<Product> p = new ArrayList<Product>();
        Product p1 = new Product("p1", 1.00);
        p1.setQuantity(1);
        p.add(p1);
        o1.setProducts(p);
        for(Product p2:o1.getProducts()){
            Log.d("product name is",p2.getName());
        }
        list.add(o1);

    } */

    private void completeOrder(Order o){
        // complete order...
        //o.setComplete(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(StoreOwner_ViewOrders.this, StoreOwnerHomepage.class);
        startActivity(back);
        finish();
    }
}