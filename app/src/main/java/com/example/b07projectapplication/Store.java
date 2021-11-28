package com.example.b07projectapplication;

import java.util.ArrayList;

public class Store {

    String storeName;
    StoreOwner storeOwner;
    ArrayList<Product> products;

    public Store(StoreOwner storeOwner) {
        this.storeName = storeOwner.storeName;
        this.storeOwner = storeOwner;
        products = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getStoreName() {
        return storeName;
    }

    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

}
