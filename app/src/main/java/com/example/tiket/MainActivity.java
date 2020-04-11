package com.example.tiket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Animation App_splash, btt;
    ImageView app_logo;
    TextView app_subtitle,by;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getUsernameLocal();

        //loadanimation
        App_splash = AnimationUtils.loadAnimation( this, R.anim.app_splash );
        btt = AnimationUtils.loadAnimation( this, R.anim.btt );
        //load element
        app_logo = findViewById( R.id.app_logo );
        by = findViewById( R.id.by );
        app_subtitle = findViewById( R.id.app_subtitle );

        //run animation
        app_logo.startAnimation( App_splash );
        app_subtitle.startAnimation( btt );
        by.startAnimation( btt );


    }
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences( USERNAME_KEY, MODE_PRIVATE );
        username_key_new = sharedPreferences.getString( username_key, "" );
        if(username_key_new.isEmpty()){
            //setting timer
            Handler handler = new Handler();
            handler.postDelayed( new Runnable() {
                @Override
                public void run() {
                    //merubah activity ke activty lain
                    Intent gotogetstarted = new Intent( MainActivity.this,GetStartedAct.class );
                    startActivity( gotogetstarted );
                    finish();

                }
            }, 2000); // 2000ms = 2s
        }
            else {
            //setting timer
            Handler handler = new Handler();
            handler.postDelayed( new Runnable() {
                @Override
                public void run() {
                    //merubah activity ke activty lain
                    Intent gotogetstarted = new Intent( MainActivity.this,HomeAct.class );
                    startActivity( gotogetstarted );
                    finish();

                }
            }, 2000); // 2000ms = 2s
        }
    }
}
