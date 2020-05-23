package com.example.chatappmvvm.interfaces;

import com.google.firebase.database.DataSnapshot;

public interface FirebaseCallBack {
    void onNewMessage(DataSnapshot dataSnapshot);
}
