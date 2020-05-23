package com.example.chatappmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatappmvvm.R;
import com.example.chatappmvvm.databinding.ActivityLoginBinding;
import com.example.chatappmvvm.interfaces.Observer;
import com.example.chatappmvvm.utils.MyUtils;
import com.example.chatappmvvm.viewmodel.LoginViewModel;

import java.util.Observable;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements Observer<String> {
    private LoginViewModel loginViewModel;
    private Dialog chatRoomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView( this, R.layout.activity_login );
        loginViewModel = new LoginViewModel();
        activityLoginBinding.setViewModel( loginViewModel );
        activityLoginBinding.setActivity( this );

    }

    public void showRoomDialog() {
        chatRoomDialog = new Dialog( this );
        chatRoomDialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        View view = LayoutInflater.from( this ).inflate( R.layout.dialog_room, null );
        Button submitRoomName =  view.findViewById( R.id.button_room_submit );
        final EditText editTextRoomName =  view.findViewById( R.id.room_name );
        submitRoomName.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.invalidateRoomName( editTextRoomName.getText().toString() );
            }
        } );
        chatRoomDialog.setContentView( view );
        chatRoomDialog.show();

    }

    private void startChatActivity(String roomName) {
        chatRoomDialog.dismiss();
        chatRoomDialog = null;
        Intent intent = new Intent( this, ChatActivity.class );
        intent.putExtra( MyUtils.EXTRA_ROOM_NAME, roomName );
        startActivity( intent );
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginViewModel.addObserver(  this );
    }

    @Override
    protected void onPause(){
        super.onPause();
        loginViewModel.removeObserver( this );
    }

    @Override
    public void onObserve(int event, String eventString) {
        if (event == MyUtils.SHOW_TOAST) {
            Toasty.info( this, eventString, Toasty.LENGTH_LONG ).show();
        } else if (event == MyUtils.OPEN_ACTIVITY) {
            startChatActivity( eventString );
        }
    }
}
