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

public class SuccesBuyTicketAct extends AppCompatActivity {

    Button btn_my_dashboard, btn_view_ticket;
    Animation App_splash, btt, ttb;
    ImageView icon_succes;
    TextView app_title, app_subtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_succes_buy_ticket );

        btn_my_dashboard = findViewById( R.id.btn_my_dashboard );
        btn_view_ticket = findViewById( R.id.btn_view_ticket );
        icon_succes = findViewById( R.id.icon_success_ticket );
        app_title = findViewById( R.id.app_title );
        app_subtitle = findViewById( R.id.app_subtitle );

        //loadanimation
        App_splash = AnimationUtils.loadAnimation( this, R.anim.app_splash );
        btt = AnimationUtils.loadAnimation( this, R.anim.btt );
        ttb = AnimationUtils.loadAnimation( this, R.anim.ttb );


        //run animation
        btn_view_ticket.startAnimation( btt );
        btn_my_dashboard.startAnimation( btt );
        icon_succes.startAnimation( App_splash );
        app_title.startAnimation( ttb );
        app_subtitle.startAnimation( ttb );



        btn_my_dashboard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotomydashboard = new Intent( SuccesBuyTicketAct.this, HomeAct.class );
                startActivity( gotomydashboard );
            }
        } );

        btn_view_ticket.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotomyprofile = new Intent( SuccesBuyTicketAct.this, MyProfileAct.class );
                startActivity( gotomyprofile );
            }
        } );
    }
}
