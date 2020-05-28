package com.example.chatappmvvm.interfaces;

import com.example.chatappmvvm.models.ChatPojo;

import java.util.ArrayList;

public interface ModelCallBack {
      void onModelUpdated(ArrayList<ChatPojo> messages);
}
