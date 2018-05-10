package com.dragfoundation.screenart;

import java.io.Serializable;

/**
 * Created by vikas on 25-12-2017.
 */

public class Users implements Serializable {

    String lastUserName;
    long time;
    String lastMessage;





    public Users() {
    }

    public Users(String lastMessage, String lastUserName, long time) {
        this.lastUserName = lastUserName;
        this.time = time;
        this.lastMessage=lastMessage;
    }


    public String getLastUserName() {
        return lastUserName;
    }

    public long getTime() {
        return time;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
