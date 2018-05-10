package com.dragfoundation.screenart;

/**
 * Created by vikas on 28-08-2017.
 */

public class Product {
    private int mImageResourceId;
    String name;
    int Price;

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return Price;
    }

    public Product(int mImageResourceId, String name, int Price) {
        this.mImageResourceId = mImageResourceId;
        this.name = name;
        this.Price = Price;
    }
}
