package com.dragfoundation.screenart;

/**
 * Created by vikas on 04-01-2018.
 */

public class Contact {
    String name,add1,add2,phone;

    public Contact(String name, String add1, String add2, String phone) {
        this.name = name;
        this.add1 = add1;
        this.add2 = add2;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAdd1() {
        return add1;
    }

    public String getAdd2() {
        return add2;
    }

    public String getPhone() {
        return phone;
    }
}
