package com.example.b07projectapplication;

import java.util.Objects;

public class Product {
    String name;
    String priceString;
    double price;

    public Product(){
    }

    public Product(String name, double price){
        this.name = name;
        this.price = price;
        this.priceString = "$" + String.valueOf( Math.round(this.price * 100.0)/100.0 );
    }

    public String getName() { return name; }
    public double getPrice() {
        return ( Math.round(price * 100.0)/100.0 );
    }
    public String getPriceString() {
        return ("$" + String.valueOf( Math.round(price * 100.0)/100.0 ));
    }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = (Math.round(price * 100.0)/100.0); }
    public void setPriceString(String priceString) { this.priceString = priceString; }

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
