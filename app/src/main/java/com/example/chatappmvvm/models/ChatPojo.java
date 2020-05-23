package com.example.chatappmvvm.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.chatappmvvm.utils.MyUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Objects;

public class ChatPojo extends BaseObservable {
    private String messageKey;
    private String timeStamp;
    private String message;
    private boolean isMine;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ChatPojo(DataSnapshot dataSnapshot) {
        HashMap<String, Object> objectHashMap = (HashMap<String, Object>)dataSnapshot.getValue();
        this.messageKey = dataSnapshot.getKey();

        assert objectHashMap != null;
        this.message = Objects.requireNonNull( objectHashMap.get( "text" ) ).toString();
        if (Objects.requireNonNull( objectHashMap.get( "senderId" ) ).toString()
                .equals( Objects.requireNonNull( FirebaseAuth.getInstance().getCurrentUser() ).getUid() )){
            isMine = true;
        }
        this.timeStamp = MyUtils.convertTime(Long.parseLong( objectHashMap.get( "time" ).toString() ));

    }

    @Bindable

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}





