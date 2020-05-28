package com.example.chatappmvvm.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;

import com.example.chatappmvvm.interfaces.FirebaseCallBack;
import com.example.chatappmvvm.interfaces.ModelCallBack;
import com.example.chatappmvvm.interfaces.Observer;
import com.example.chatappmvvm.models.ChatPojo;
import com.example.chatappmvvm.models.Message;
import com.example.chatappmvvm.utils.FirebaseManager;
import com.example.chatappmvvm.utils.MyUtils;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class ChatViewModel extends BaseObservable implements FirebaseCallBack, ModelCallBack {
    private String roomName;
    private Message messageModel;
    public ArrayList<Observer> observers;


    public ChatViewModel(String roomName){
        this.roomName = roomName;
        messageModel = new Message();
        observers = new ArrayList<>(  );
    }

    public void sendMessageToFirebase(String message) {
        if (!message.trim().equals( " " )){
            FirebaseManager.getInstance(roomName, this).sendMessageToFirebase(message);

        }
    }
    public void setListener(){
        FirebaseManager.getInstance( roomName, this ).addMessageListeners();
    }

    public void onDestroy() {
        FirebaseManager.getInstance( roomName, this ).removeListeners();
        FirebaseManager.getInstance( roomName, this ).destroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNewMessage(DataSnapshot dataSnapshot){
        messageModel.addMessages( dataSnapshot, this );
    }

    @Override
    public void onModelUpdated(ArrayList<ChatPojo> messages) {
        if (messages.size()>0) {
            notifyObservers( MyUtils.UPDATE_MESSAGE, messages );
        }
    }
    public void addObserver(Observer client) {
        if (!observers.contains( client )) {
            observers.add( client );
        }
    }

    public void removeObserver(Observer clientToRemove) {
        if (!observers.contains( clientToRemove )){
            observers.remove( clientToRemove );
        }
    }
    public void notifyObservers(int eventType, ArrayList<ChatPojo> messages) {
        for (int i =0; i < observers.size(); i++) {
            observers.get( i ).onObserve( eventType, messages );
        }
    }
}
