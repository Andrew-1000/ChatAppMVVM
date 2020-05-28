package com.example.chatappmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.chatappmvvm.R;
import com.example.chatappmvvm.adapter.ChatAdapter;
import com.example.chatappmvvm.databinding.ActivityChatBinding;
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
        activityChatBinding.setViewModel( chatViewModel );
        activityChatBinding.setActivity( this );
        activityChatBinding.recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        chatViewModel.addObserver( this );
        chatViewModel.setListener();
    }
    public void sendMessage() {
        chatViewModel.sendMessageToFirebase( activityChatBinding.edittextChatMessage.getText().toString() );
        activityChatBinding.edittextChatMessage.getText().clear();
    }

    @Override
    public void onObserve(int event, ArrayList<ChatPojo> eventMessage) {
        ChatAdapter chatAdapter = new ChatAdapter(this, eventMessage);
        activityChatBinding.recyclerView.setAdapter( chatAdapter );
        activityChatBinding.recyclerView.scrollToPosition( eventMessage.size()-1 );
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        chatViewModel.removeObserver( this );
        chatViewModel.onDestroy();
    }
}
