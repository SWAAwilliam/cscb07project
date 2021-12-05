package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer_ViewProducts extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    ProductAdapter adapter;
    ArrayList<Product> list;

    //Keeps all the product keys that are selected by the customer
    ArrayList<Integer> productIndex;
    //Keeps all the product quantities that are selected by the customer
    ArrayList<Integer> productQuantity;
    Button viewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_products);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        //get the userId of the clicked store
        String id = bundle.getString("userid");
        //System.out.println("asede: " + id);
        productIndex = new ArrayList<>();
        productQuantity = new ArrayList<>();
        //open the Cart screen
        viewCart = findViewById(R.id.view_cart);
        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToCart();
            }
        });

        recyclerView = findViewById(R.id.product_view);
        ref = FirebaseDatabase.getInstance().getReference("users").child(id).child("products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ProductAdapter(this, list);
        recyclerView.setAdapter(adapter);


        adapter.setRecyclerViewClickListener(new ProductAdapter.buttonClickListener() {
            @Override
            public void onClick(int position) {
                Product p = list.get(position);
                productIndex.add(position);
                productQuantity.add(p.getQuantity());
                //Toast.makeText(Customer_ViewProducts.this, "Product added", Toast.LENGTH_SHORT).show();
            }
        });

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

    private void sendToCart(){
        //pass the arrayList to cart
        Intent intent = new Intent(Customer_ViewProducts.this, Customer_ViewCart.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("BUNDLE",productIndex);
        intent.putExtra("BUNDLE2",productQuantity);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("userid");
        intent.putExtra("userid",id);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent back = new Intent(Customer_ViewProducts.this, Customer_ViewMyStores.class);
        startActivity(back);
        finish();
    }
}