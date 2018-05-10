package com.dragfoundation.screenart;

import java.io.Serializable;

/**
 * Created by vikas on 27-08-2017.
 */

public class OrderStat implements Serializable {

        private long date;
        private int quantity;
        String name;
        String add;



    int order;

    int category,type,weight;
        String fileName,comments;

    public OrderStat(int order, String name, long date, int quantity, int category, int type, int weight, String fileName, String comments,String add) {
        this.order=order;
        this.date = date;
        this.name=name;
        this.quantity = quantity;
        this.category = category;
        this.type = type;
        this.weight = weight;
        this.fileName = fileName;
        this.comments = comments;
        this.add=add;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public long getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }


    public int getCategory() {
        return category;
    }

    public int getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public String getFileName() {
        return fileName;
    }

    public String getComments() {
        return comments;
    }

    public String getAdd() {
        return add;
    }
}
