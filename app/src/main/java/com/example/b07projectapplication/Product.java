package com.example.b07projectapplication;

import java.util.Objects;

public class Product {
    String name;
    double price;

    public Product(){
    }

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if ( this.getClass() != obj.getClass() )
            return false;

        Product other = (Product) obj;
        return Double.compare(other.price, price) == 0 && Objects.equals(name, other.name);
    }

}
