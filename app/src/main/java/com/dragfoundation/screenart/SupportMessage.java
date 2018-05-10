package com.dragfoundation.screenart;

/**
 * Created by vikas on 11-10-2017.
 */

public class SupportMessage {
    private String text;
    private String name;
    private long date;



    public SupportMessage(String text, String name, long date) {
        this.text = text;
        this.name = name;
        this.date = date;
    }

    public SupportMessage() {
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }






    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
