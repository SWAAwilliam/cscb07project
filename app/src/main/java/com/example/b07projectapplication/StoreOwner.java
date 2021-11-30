package com.example.b07projectapplication;

import java.util.HashSet;

public class StoreOwner extends Person{
    //need a way to store all the orders
    String storeName;
    HashSet<Product> products;


    public StoreOwner(){
    }

    public StoreOwner(String userUID, String firstName, String lastName, String storeName, boolean isOwner){
        super(userUID, firstName, lastName, isOwner);
        this.storeName = storeName;
    }

    public void setStoreName(String storeName) { this.storeName = storeName; }
    public String getStoreName() { return this.storeName; }

    public void addProduct(Product product){
        //Add a product
        products.add(product);
    }

    public HashSet<Product> getProducts(){
        //Return a HashSet of Products
        return null;
    }

    public void removeProduct(){
        //Remove a product
    }

}
