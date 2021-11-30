package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StoreOwner_ViewProducts extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private StoreOwner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_view_products);
        getSupportActionBar().hide();


        //READ THE CURRENT USER FROM THE DATABASE
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        String userUID = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("students").child(userUID);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                owner = snapshot.getValue(StoreOwner.class);
                updateListView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StoreOwner_ViewProducts.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        };

        updateListView();
    }

    public void updateListView(){
        //Update the listView
        //HashSet<Product> productSet = owner.getProducts();

        //TESTING CODE
        HashSet<Product> productSet = new HashSet<>();
        Product p1 = new Product("Product 1", 5.00);
        Product p2 = new Product("Product 2", 16.32);
        Product p3 = new Product("Product 3", 1.69);
        productSet.add(p1);
        productSet.add(p2);
        productSet.add(p3);
        //END OF TESTING CODE

        HashMap<String, String> productMap = new HashMap<>();
        ListView list = (ListView) findViewById(R.id.productList);

        if ( productSet.isEmpty() ){
            Toast.makeText(StoreOwner_ViewProducts.this, "No Recorded Products, Please Add New Product!", Toast.LENGTH_SHORT).show();
        }
        else{
            for (Product product: productSet){
                //Put all products in a HashMap with name as key, price as value
                productMap.put( product.getName(), product.getPriceString() );
            }

            //CODE COURTESY OF https://www.youtube.com/watch?v=VYDLTBjdliY
            List<HashMap<String, String>> listItems = new ArrayList<>();
            SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.owner_listproducts_resources,
                    new String[]{"First Line", "Second Line"}, new int[]{R.id.listViewText1, R.id.listViewText2});

            Iterator it = productMap.entrySet().iterator();
            while ( it.hasNext() ){
                HashMap<String, String> resultsMap = new HashMap<>();
                Map.Entry pair = (Map.Entry)it.next();
                resultsMap.put( "First Line", pair.getKey().toString() );
                resultsMap.put( "Second Line", pair.getValue().toString() );
                listItems.add(resultsMap);
            }
            list.setAdapter(adapter);
        }

    }


}