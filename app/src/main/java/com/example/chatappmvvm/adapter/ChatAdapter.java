package com.example.chatappmvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappmvvm.BR;
import com.example.chatappmvvm.R;
import com.example.chatappmvvm.databinding.RowChatAdapterBinding;
import com.example.chatappmvvm.models.ChatPojo;


import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private ArrayList<ChatPojo> chatList;
    private Context context;

    public ChatAdapter(Context context, ArrayList<ChatPojo> chatList){
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.row_chat_adapter, parent, false );
        RowChatAdapterBinding binding = DataBindingUtil.bind( view );
        assert binding != null;
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder holder, final int position) {
        holder.getBinding().setVariable( BR.chatMessage, chatList.get( position ));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
