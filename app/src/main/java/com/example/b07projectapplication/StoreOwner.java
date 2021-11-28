package com.example.b07projectapplication;

public class StoreOwner extends Person{
    //need a way to store all the orders
    //need a way to store all the products
    String storeName;


    public StoreOwner(){
    }

    public StoreOwner(String userUID, String firstName, String lastName, String storeName, boolean isOwner){
        super(userUID, firstName, lastName, isOwner);
        this.storeName = storeName;
    }

    public void setStoreName(String storeName) { this.storeName = storeName; }
    public String getStoreName() { return this.storeName; }

}
