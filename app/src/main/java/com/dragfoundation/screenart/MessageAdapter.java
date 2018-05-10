package com.dragfoundation.screenart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikas on 11-10-2017.
 */

public class MessageAdapter extends ArrayAdapter<SupportMessage> {
    String uid;
    String check;
    Context c;
    private List<SupportMessage> chatMessageList = new ArrayList<SupportMessage>();
    public MessageAdapter(Context context, int resource , List<SupportMessage> objects, String userid) {
        super(context, resource);
        uid=userid;
        c=context;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        SupportMessage message = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (message.getName().equalsIgnoreCase(uid) && message.getName()!=null) {
            row = inflater.inflate(R.layout.rightmessage, parent, false);
        } else {
            row = inflater.inflate(R.layout.leftmessage, parent, false);
        }
        TextView messageTextView = (TextView) row.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) row.findViewById(R.id.nameTextView);
        messageTextView.setText(message.getText());

        authorTextView.setText(message.getName());
        return row;
    }


}
