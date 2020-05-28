package com.example.chatappmvvm.models;


import com.example.chatappmvvm.interfaces.ModelCallBack;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Message {
    private ArrayList<ChatPojo> messages;
    public void addMessages(DataSnapshot dataSnapshot, ModelCallBack callback) {
        if (messages == null) {
            messages = new ArrayList<>(  );
        }
        ChatPojo chatPojo = new ChatPojo( dataSnapshot );
        messages.add( chatPojo );
        callback.onModelUpdated(messages);
    }
}
