package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Customer_ViewCart extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    CartAdapter adapter;
    ArrayList<Product> fullCart;
    TextView total;
    Button save;
    Button addToOrder;
    double cartTotal;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_cart);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        final ArrayList<Integer> pIndex = bundle.getIntegerArrayList("BUNDLE");
        final ArrayList<Integer> pQuantity = bundle.getIntegerArrayList("BUNDLE2");
        id = bundle.getString("userid");
        recyclerView = findViewById(R.id.cart_view);
        ref = FirebaseDatabase.getInstance().getReference("users").child(id).child("products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fullCart = new ArrayList<>();
        adapter = new CartAdapter(this,fullCart);
        recyclerView.setAdapter(adapter);
        total = findViewById(R.id.cart_total);
        save = findViewById(R.id.save_cart);
        addToOrder = findViewById(R.id.add_to_order);


        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                cartTotal = 0;
                if (task.isSuccessful()){

                    for (DataSnapshot data: task.getResult().getChildren()){
                        String s = data.getKey();
                        if (pIndex.contains(Integer.valueOf(s))){
                            Product p = data.getValue(Product.class);
                            int j = pIndex.indexOf(Integer.valueOf(s));
                            p.setQuantity(pQuantity.get(j));
                            if(p.getQuantity() > 0){
                            fullCart.add(p);
                            cartTotal += p.getQuantity() * p.getPrice();}
                        }
                    }
                    adapter.notifyDataSetChanged();

                }
                total.setText("Your total is: $"+String.valueOf(cartTotal));
            }
        });

        adapter.setRecyclerViewClickListener(new CartAdapter.buttonClickListener() {
            @Override
            public void onClick(int position) {
                Product r = fullCart.get(position);
                //Log.d("removeFromCart",r.getName()+" Removed from cart");
                cartTotal -= r.getQuantity() * r.getPrice();
                total.setText("Your total is: $"+String.valueOf(cartTotal));
                fullCart.remove(r);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void sendToOrder(){
        //fullCart contains all cart products and quantities
    }

    private void saveCart(){

    }
    @Override
    public void onBackPressed() {
        Intent back = new Intent(Customer_ViewCart.this, Customer_ViewProducts.class);
        back.setFlags(back.FLAG_ACTIVITY_CLEAR_TASK|back.FLAG_ACTIVITY_NEW_TASK);
        back.putExtra("userid",id);
        startActivity(back);
        finish();
    }
}