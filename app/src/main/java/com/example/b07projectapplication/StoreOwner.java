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

    public boolean addProduct(Product product){
        //Add a product, returns true on success, false if duplicate
        if (products.contains(product)){
            return false;
        }
        products.add(product);
        return true;
    }

    public HashSet<Product> getProducts(){
        //Return a HashSet of Products
        return this.products;
    }

    public boolean removeProduct(Product product){
        //Remove a product, returns true on success, false if product does not exist
        if ( !products.contains(product) ){
            return false;
        }
        products.remove(product);
        return true;
    }

}
