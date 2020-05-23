package com.example.chatappmvvm.interfaces;

public interface Observer<T> {
    void onObserve(int event, T eventMessage);
}
