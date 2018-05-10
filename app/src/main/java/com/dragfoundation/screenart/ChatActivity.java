package com.dragfoundation.screenart;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private MessageAdapter mMessageAdapter2;
    private EditText mMessageEditText;
    private ImageButton mSendButton;

    private String mUsername;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference, firebaseRefrence;
    private ChildEventListener mChildEventListener;
    SharedPreferences user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        user = getApplicationContext().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);

        final String userid = user.getString("ID", null);

        mUsername = userid;

        mFirebaseDatabase=FirebaseDatabase.getInstance();



        mDatabaseReference=mFirebaseDatabase.getReference().child("chatmessages/"+mUsername);
        firebaseRefrence=mFirebaseDatabase.getReference().child("users/"+mUsername);

        // Initialize references to views
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);

        // Initialize messageright ListView and its adapter
        List<SupportMessage> supportMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.rightmessage, supportMessages,userid);
        mMessageListView.setAdapter(mMessageAdapter);



    ;

        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a messageright and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                long time= System.currentTimeMillis();
                SupportMessage supportMessage = new SupportMessage(mMessageEditText.getText().toString(), mUsername,time);
                mDatabaseReference.push().setValue(supportMessage);


                Users u=new Users(mMessageEditText.getText().toString(), mUsername,time);

                firebaseRefrence.setValue(u);
                // Clear input box
                mMessageEditText.setText("");
            }
        });

        mChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SupportMessage supportMessage =dataSnapshot.getValue(SupportMessage.class);
                if(supportMessage.getName()!=null)
                {
                    mMessageAdapter.add(supportMessage);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        mDatabaseReference.addChildEventListener(mChildEventListener);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseReference.removeEventListener(mChildEventListener);
    }
}
