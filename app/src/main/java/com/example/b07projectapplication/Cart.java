package com.example.b07projectapplication;

import java.util.ArrayList;
import java.util.Objects;

public class Cart {
    String storeName;
    StoreOwner storeOwner;
    ArrayList<Product> cartProducts;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }

    public ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<Product> cartProducts) {
        this.cartProducts = new ArrayList<Product>();
        for (Product product: cartProducts){
            this.cartProducts.add(product);
        }
    }

    //start a new cart
    public Cart(StoreOwner storeOwner){
        this.storeOwner = storeOwner;
        storeName = storeOwner.storeName;
        cartProducts = new ArrayList<Product>();
    }

    public void addToCart(Product product){
        if (cartProducts.contains(product)){
            int q = product.quantity;
            int index = cartProducts.indexOf(product);
            Product updatedProduct = new Product();
            Product currentProduct = cartProducts.get(index);
            updatedProduct.name = currentProduct.getName();
            updatedProduct.price = currentProduct.getPrice();
            updatedProduct.priceString = currentProduct.getPriceString();
            updatedProduct.quantity = q + currentProduct.quantity;
            cartProducts.set(index, updatedProduct);
        }
        cartProducts.add(product);
    }

    public boolean removeFromCart(Product product){
        if(cartProducts.contains(product)){
            cartProducts.remove(product);
            return true;
        }
        else {
            return false;
        }
    }

    //remove specified amount of product from the cart
    public boolean removeFromCart(Product product, int number){

        if(cartProducts.contains(product)){
            if(product.quantity <= number ){
                removeFromCart(product);
            }
            else {
                int index = cartProducts.indexOf(product);
                Product updatedProduct = new Product();
                Product currentProduct = cartProducts.get(index);
                updatedProduct.name = currentProduct.getName();
                updatedProduct.price = currentProduct.getPrice();
                updatedProduct.priceString = currentProduct.getPriceString();
                updatedProduct.quantity = currentProduct.quantity - number;
                cartProducts.set(index, updatedProduct);
            }
            return true;
        }
        else {
            return false;
        }
    }


    //removes every product from the cart
    public void resetCart(){
        for (Product product: cartProducts){
            this.cartProducts.remove(product);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeName, storeOwner, cartProducts);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if ( this.getClass() != obj.getClass() )
            return false;

        Cart other = (Cart) obj;
        return Objects.equals(storeName, other.storeName) && Objects.equals(storeOwner, other.storeOwner) &&
                Objects.equals(cartProducts, other.cartProducts);
    }
}
