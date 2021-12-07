package com.example.b07projectapplication;

import java.util.ArrayList;

public class StoreOwner extends Person{
    String storeName;
    ArrayList<Product> products;
    ArrayList<Order> orders;



    public StoreOwner(){
    }

    public StoreOwner(String userUID, String firstName, String lastName, String storeName, boolean isOwner){
        super(userUID, firstName, lastName, isOwner);
        this.storeName = storeName;
    }

    public void setStoreName(String storeName) { this.storeName = storeName; }
    public String getStoreName() { return this.storeName; }

    public void setProducts(ArrayList<Product> products){
        this.products = new ArrayList<Product>();
        for (Product product: products){
            this.products.add(product);
        }
    }
    public ArrayList<Product> getProducts(){
        return new ArrayList<Product>(products);
    }

    public void setOrders(ArrayList<Order> orders){
        this.orders = new ArrayList<Order>();
        for(Order order: orders){
            this.orders.add(order);
        }
    }
    public ArrayList<Order> getOrders(){ return new ArrayList<Order>(orders); }

    public boolean addProduct(Product product){
        //Add a product, returns true on success, false if duplicate
        if (products.contains(product)){
            return false;
        }
        products.add(product);
        return true;
    }

    public boolean removeProduct(Product product){
        //Remove a product, returns true on success, false if product does not exist
        if ( !products.contains(product) ){
            return false;
        }
        products.remove(product);
        return true;
    }

    public boolean addOrder(Order order){
        //Add a product, returns true on success, false if duplicate
        if (orders.contains(order)){
            return false;
        }
        orders.add(order);
        return true;
    }

    public boolean removeOrder(Order order){
        //Remove a product, returns true on success, false if product does not exist
        if ( !orders.contains(order) ){
            return false;
        }
        orders.remove(order);
        return true;
    }

}
