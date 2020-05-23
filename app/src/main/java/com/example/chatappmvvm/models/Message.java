package com.example.chatappmvvm.models;

import android.os.Build;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;

import com.example.chatappmvvm.interfaces.ModelCallBack;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Message {
    private ArrayList<ChatPojo> messages;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addMessages(DataSnapshot dataSnapshot, ModelCallBack callback) {
        if (messages == null) {
            messages = new ArrayList<>(  );
        }
        ChatPojo chatPojo = new ChatPojo( dataSnapshot );
        messages.add( chatPojo );
        callback.onModelUpdated(messages);
    }
}
