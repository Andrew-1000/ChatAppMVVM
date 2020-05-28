package com.example.chatappmvvm.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappmvvm.databinding.RowChatAdapterBinding;

class BindingViewHolder extends RecyclerView.ViewHolder {

    private RowChatAdapterBinding binding;

    BindingViewHolder(RowChatAdapterBinding binding) {
        super(binding.getRoot());
        setBinding(binding);
    }

    public void setBinding(RowChatAdapterBinding binding) {
        this.binding = binding;
    }
    public RowChatAdapterBinding getBinding() {
        return binding;
    }
}
