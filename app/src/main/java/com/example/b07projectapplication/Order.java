package com.example.b07projectapplication;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

    String ownerUID;        //same as storeUID
    String customerUID;
    String customerName;    //so that the owner can know the name of customer
    boolean isComplete;
    ArrayList<Product> orderProducts;

    public void Order(){
    }

    public String getOwnerUID() { return ownerUID; }
    public void setOwnerUID(String ownerUID) { this.ownerUID = ownerUID;}

    public String getCustomerUID() { return customerUID; }
    public void setCustomerUID(String customerUID) { this.customerUID = customerUID; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public boolean isComplete() { return isComplete; }
    public void setComplete(boolean complete) { isComplete = complete; }

    public ArrayList<Product> getProducts() { return orderProducts; }
    public void setOrderProducts(ArrayList<Product> products) {
        this.orderProducts = new ArrayList<Product>();
        for (Product product: products){
            this.orderProducts.add(product);
        }
    }

    public boolean addProduct(Product product){
        //Add a product, returns true on success, false if duplicate
        if (orderProducts.contains(product)){
            return false;
        }
        orderProducts.add(product);
        return true;
    }

    public boolean removeProduct(Product product){
        //Remove a product, returns true on success, false if product does not exist
        if ( !orderProducts.contains(product) ){
            return false;
        }
        orderProducts.remove(product);
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerUID, customerUID, customerName, orderProducts);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if ( this.getClass() != obj.getClass() )
            return false;

        Order other = (Order) obj;
        return Objects.equals(ownerUID, other.ownerUID) && Objects.equals(customerUID, other.customerUID) &&
                Objects.equals(customerName, other.customerName) && Objects.equals(orderProducts, other.orderProducts) &&
                Objects.equals(isComplete, other.isComplete);
    }


}
