package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewStores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stores);
        getStores();
    }

    public void viewStore(ArrayList<Store> stores) {
        if (stores == null || stores.size() == 0) {
            Log.i("check", "No stores found");
            return;
        }
        int x = (int) (Math.ceil(stores.size() / 3.0));
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < x; i++) {
            LinearLayout l = new LinearLayout(this);
            l.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 3 && (j + (i * 3)) < stores.size(); j++) {
                Button store = new Button(this);
                store.setLayoutParams(new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.MATCH_PARENT));

                store.setText(stores.get(j + (i * 3)).storeName);
                store.setId(j + (i * 3));
                l.addView(store);
            }
            layout.addView(l);
        }
        setContentView(layout);
    }

    public void getStores() {
        ArrayList<Store> stores = new ArrayList<Store>();
        /*// Testing viewStore...
        StoreOwner s = new StoreOwner("test", "s", "p", "Random Store", true);
        Store store = new Store(s);
        stores.add(store);
        //*/

        //Reading from a realtime database using a persistent listener
        DatabaseReference ref =
                FirebaseDatabase.getInstance().getReference("users");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("check", "data changed");
                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                    Log.i("check", child.toString());
                    StoreOwner p = child.getValue(StoreOwner.class);
//                    Log.i("check", p.getUserUID());
                    if (p.isOwner) {
//                        Log.i("check", "owner found");
                        Store a = new Store(p);
                        stores.add(a);
//                        Log.i("check", stores.size()+"");
                    }
                }
                Log.i("check", "Total Stores found = " + stores.size());
                viewStore(stores);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        ref.addValueEventListener(listener);
        return;
    }
}