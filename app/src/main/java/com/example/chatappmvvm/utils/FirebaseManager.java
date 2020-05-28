package com.example.chatappmvvm.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chatappmvvm.interfaces.FirebaseCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseManager implements ChildEventListener {
    private volatile static FirebaseManager firebaseManager;
    private DatabaseReference messageReference;
    private FirebaseCallBack mFirebaseCallBack;

    public static synchronized FirebaseManager getInstance(String roomName, FirebaseCallBack firebaseCallBack){
        if (firebaseManager == null) {
            synchronized(FirebaseManager.class) {
                firebaseManager = new FirebaseManager(roomName, firebaseCallBack);
            }
        }
        return firebaseManager;
    }

    private FirebaseManager(String roomName, FirebaseCallBack callBacks) {
        messageReference = FirebaseDatabase.getInstance().getReference().child( roomName);
        this.mFirebaseCallBack = callBacks;
    }
    public void addMessageListeners() {
        messageReference.addChildEventListener( this );
    }

    public void removeListeners() {
        messageReference.removeEventListener( this );
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        mFirebaseCallBack.onNewMessage( dataSnapshot );
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
    public void sendMessageToFirebase(String message) {
        Map<String, Object> map = new HashMap<>(  );
        map.put( "text", message );
        map.put( "time",System.currentTimeMillis() );
        map.put( "senderId", Objects.requireNonNull( FirebaseAuth.getInstance().getCurrentUser() ).getUid() );

        String keyToPush = messageReference.push().getKey();
        assert keyToPush != null;
        messageReference.child( keyToPush   ).setValue( map );
    }

    public void destroy() {
        firebaseManager = null;
        mFirebaseCallBack = null;
    }
}
