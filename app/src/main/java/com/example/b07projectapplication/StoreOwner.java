package com.example.b07projectapplication;

public class StoreOwner extends Person{
    //need a way to store all the orders
    //need a way to store all the products
    String storeName;


    public StoreOwner(){
    }

    public StoreOwner(String userUID, String firstName, String lastName, String storeName){
        super(userUID, firstName, lastName);
        this.storeName = storeName;
    }


}
