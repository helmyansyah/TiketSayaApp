package com.example.tiket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccesRegisterAct extends AppCompatActivity {

    Button btn_explore;
    Animation App_splash, btt, ttb;
    ImageView icon_succes;
    TextView app_title, app_subtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_succes_register );

        btn_explore = findViewById( R.id.btn_explore );
        icon_succes = findViewById( R.id.icon_success );
        app_subtitle = findViewById( R.id.app_subtitle );
        app_title = findViewById( R.id.app_title );

        //loadanimation
        App_splash = AnimationUtils.loadAnimation( this, R.anim.app_splash );
        btt = AnimationUtils.loadAnimation( this, R.anim.btt );
        ttb = AnimationUtils.loadAnimation( this, R.anim.ttb );

        //run animation
        btn_explore.startAnimation( btt );
        icon_succes.startAnimation( App_splash );
        app_title.startAnimation( ttb );
        app_subtitle.startAnimation( ttb );

        btn_explore.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotohome = new Intent(SuccesRegisterAct.this, HomeAct.class);
                startActivity( gotohome );
            }
        } );
    }
}
