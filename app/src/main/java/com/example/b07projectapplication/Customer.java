package com.example.b07projectapplication;

import java.util.ArrayList;

public class Customer extends Person{
    //Need a way to keep track of orders made by a customer
    ArrayList<Order> orders;

    public Customer(){
    }

    public Customer(String userUID, String firstName, String lastName, boolean isOwner){
        super(userUID, firstName, lastName, isOwner);
    }

    public void setOrders(ArrayList<Order> orders){
        this.orders = new ArrayList<Order>();
        for (Order order: orders){
            this.orders.add(order);
        }
    }
    public ArrayList<Order> getOrders(){
        return new ArrayList<Order>(orders);
    }

    public boolean addOrder(Order order){
        if (orders.contains(order)){
            return false;
        }
        orders.add(order);
        return true;
    }

    public boolean removeOrder(Order order){
        if ( !orders.contains(order) ){
            return false;
        }
        orders.remove(order);
        return true;
    }
}
