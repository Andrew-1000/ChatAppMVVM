package com.example.chatappmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.os.Bundle;

import com.example.chatappmvvm.R;
import com.example.chatappmvvm.databinding.ActivityLoginBinding;
import com.example.chatappmvvm.viewmodel.LoginViewModel;

import java.util.Observable;
import java.util.Observer;

public class LoginActivity extends AppCompatActivity implements Observer<String> {
    private LoginViewModel loginViewModel;
    private Dialog chatRoomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView( this, R.layout.activity_login );

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
