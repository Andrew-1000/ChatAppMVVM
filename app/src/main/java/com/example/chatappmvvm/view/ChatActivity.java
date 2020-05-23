package com.example.chatappmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.chatappmvvm.R;
import com.example.chatappmvvm.interfaces.Observer;
import com.example.chatappmvvm.models.ChatPojo;
import com.example.chatappmvvm.utils.MyUtils;
import com.example.chatappmvvm.viewmodel.ChatViewModel;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements Observer<ArrayList<ChatPojo>> {

    private ActivityChatBinding activityChatBinding;
    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chat );

        activityChatBinding = DataBindingUtil.setContentView( this, R.layout.activity_chat );
        chatViewModel = new ChatViewModel(getIntent().getStringExtra( MyUtils.EXTRA_ROOM_NAME ));



    }
}
