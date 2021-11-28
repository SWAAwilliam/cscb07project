package com.example.b07projectapplication;

public class Customer extends Person{
    //Need a way to keep track of orders made by a customer

    public Customer(){
    }

    public Customer(String userUID, String firstName, String lastName, boolean isOwner){
        super(userUID, firstName, lastName, isOwner);
    }

}
