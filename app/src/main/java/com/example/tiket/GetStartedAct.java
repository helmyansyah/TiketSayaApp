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

public class GetStartedAct extends AppCompatActivity {
    Animation ttb, btt;
    Button btn_sign_in,btn_new_account_create;
    ImageView imageView2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_started );
        //loadanimation
        ttb = AnimationUtils.loadAnimation( this, R.anim.ttb );
        btt = AnimationUtils.loadAnimation( this, R.anim.btt );
        //load element
        imageView2 = findViewById( R.id.imageView2);
        textView = findViewById( R.id.textView );

        btn_sign_in = findViewById( R.id.btn_sign_in );
        btn_new_account_create = findViewById( R.id.btn_new_account_create );


        //run animation
        imageView2.startAnimation( ttb );
        textView.startAnimation( ttb );
        btn_new_account_create.startAnimation( btt );
        btn_sign_in.startAnimation( btt );

        btn_sign_in.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosignin = new Intent( GetStartedAct.this, SignInAct.class );
                startActivity( gotosignin );
            }
        } );

        btn_new_account_create.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoregisterone = new Intent( GetStartedAct.this, RegisterOneAct.class );
                startActivity( gotoregisterone );
            }
        } );

    }
}
