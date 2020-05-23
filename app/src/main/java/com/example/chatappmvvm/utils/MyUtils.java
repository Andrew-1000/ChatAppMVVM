package com.example.chatappmvvm.utils;


import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyUtils {
    public static String MESSAGE_AUTHENTICATION_FAILED = "Authentication Failed, Please check your internet Connection";
    public static String MESSAGE_INVALID_ROOM_NAME = "Enter a valid name";
    public static String EXTRA_ROOM_NAME = "EXTRA_ROOM_NAME";
    public static final int OPEN_ACTIVITY = 1;
    public static final int SHOW_TOAST = 2;
    public static final int UPDATE_MESSAGE = 1;

    @SuppressLint("SimpleDateFormat")
    public static String convertTime(long timeStamp) {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat( "hh:mm" );
        Date date = new Date( timeStamp );
        simpleDateFormat.setTimeZone( TimeZone.getDefault() );
        return simpleDateFormat.format( date );
    }
}
