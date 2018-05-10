package com.dragfoundation.screenart;

/**
 * Created by vikas on 22-12-2017.
 */

public class Catalogue {
    int id;
    String Product;
    int Price;
    int quantity;

    public Catalogue(int id, String product, int price, int quantity) {
        this.id = id;
        Product = product;
        Price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return Product;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return quantity;
    }
}
