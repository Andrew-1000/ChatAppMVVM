package com.example.chatappmvvm.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappmvvm.databinding.RowChatAdapterBinding;

class BindingHolder extends RecyclerView.ViewHolder {
    private RowChatAdapterBinding binding;

    BindingHolder (RowChatAdapterBinding binding) {
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
