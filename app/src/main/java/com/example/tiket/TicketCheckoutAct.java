package com.example.tiket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class TicketCheckoutAct extends AppCompatActivity {

    Button btn_buy_ticket, btn_plus, btn_mines;
    TextView textJumlahTicket, TextMyBalance, totalharga, nama_wisata, lokasi, ketentuan;
    Integer ValueJumlahTiket = 1;
    Integer MyBalance = 0;
    Integer ValueTotalHarga = 0;
    Integer ValueHargaTiket = 0;
    ImageView notice_uang;
    LinearLayout btn_back;
    Integer sisa_balance = 0;

    DatabaseReference reference, reference2, reference3, reference4;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    String date_wisata = "";
    String time_wisata = "";

    // generate nomor integer secara random
    // karena kita ingin membuat transaksi secara unik
    Integer nomor_transaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ticket_checkout );

        getUsernameLocal();

        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_tiket_baru = bundle.getString( "jenis_tiket" );

        btn_plus = findViewById( R.id.btn_plus );
        btn_back = findViewById( R.id.btn_back );
        btn_mines = findViewById( R.id.btn_mines );
        textJumlahTicket = findViewById( R.id.textJumlahTicket );
        TextMyBalance = findViewById( R.id.TextMyBalance );
        totalharga = findViewById( R.id.totalharga );
        btn_buy_ticket = findViewById( R.id.btn_buy_ticket );
        notice_uang = findViewById( R.id.notice_uang );
        nama_wisata = findViewById( R.id.nama_wisata );
        lokasi = findViewById( R.id.lokasi );
        ketentuan = findViewById( R.id.ketentuan );

        // setting value baru untuk beberapa komponen
        textJumlahTicket.setText( ValueJumlahTiket.toString() );



        // secara default
        btn_mines.animate().alpha( 0 ).setDuration( 300 ).start();
        btn_mines.setEnabled( false );
        notice_uang.setVisibility( View.GONE );

        // mengambil data user dari firebase
        reference2 = FirebaseDatabase.getInstance().getReference().child( "Users" ).child( username_key_new);
        reference2.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MyBalance = Integer.valueOf(  dataSnapshot.child( "user_ballance" ).getValue().toString());
                TextMyBalance.setText( "US$ " + MyBalance +"" );
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        // mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child( "Wisata" ).child( jenis_tiket_baru );
        reference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // menimpa data yang ada dengan data yang baru
                nama_wisata.setText( dataSnapshot.child( "nama_wisata" ).getValue().toString() );
                lokasi.setText( dataSnapshot.child( "lokasi" ).getValue().toString() );
                ketentuan.setText( dataSnapshot.child( "ketentuan" ).getValue().toString() );
                date_wisata = dataSnapshot.child( "date_wisata" ).getValue().toString();
                time_wisata = dataSnapshot.child( "time_wisata" ).getValue().toString();
                ValueHargaTiket = Integer.valueOf(  dataSnapshot.child( "harga_tiket" ).getValue().toString());

                ValueTotalHarga = ValueHargaTiket * ValueJumlahTiket;
                totalharga.setText( "US$ "+ValueTotalHarga +"" );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        btn_mines.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueJumlahTiket-=1;
                textJumlahTicket.setText( ValueJumlahTiket.toString() );
                if (ValueJumlahTiket < 2){
                    btn_mines.animate().alpha( 0 ).setDuration( 300 ).start();
                    btn_mines.setEnabled( false );
                }
                ValueTotalHarga = ValueHargaTiket * ValueJumlahTiket;
                totalharga.setText( "US$"+ValueTotalHarga +"" );
                if (ValueTotalHarga < MyBalance){
                    btn_buy_ticket.animate().translationY( 0 ).alpha( 1 ).setDuration( 350 ).start();
                    btn_buy_ticket.setEnabled( true );
                    TextMyBalance.setTextColor( Color.parseColor("#203DD1")  );
                    notice_uang.setVisibility( View.GONE );
                }
            }
        } );

        btn_plus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueJumlahTiket+=1;
                textJumlahTicket.setText( ValueJumlahTiket.toString() );
                if (ValueJumlahTiket > 1){
                    btn_mines.animate().alpha( 1 ).setDuration( 300 ).start();
                    btn_mines.setEnabled( true );
                }
                ValueTotalHarga = ValueHargaTiket * ValueJumlahTiket;
                totalharga.setText( "US$"+ValueTotalHarga +"" );
                if (ValueTotalHarga > MyBalance){
                    btn_buy_ticket.animate().translationY( 250 ).alpha( 0 ).setDuration( 350 ).start();
                    btn_buy_ticket.setEnabled( false );
                    TextMyBalance.setTextColor( Color.parseColor("#D1206B")  );
                    notice_uang.setVisibility( View.VISIBLE );
                }
            }
        } );


        btn_buy_ticket.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // menyimpan data user kepada firebase dan membuat tabel baru "MyTickets
                reference3 = FirebaseDatabase.getInstance()
                        .getReference().child( "MyTickets" )
                        .child( username_key_new ).child( nama_wisata.getText().toString() + nomor_transaksi);
                reference3.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child( "id_ticket" ).setValue(nama_wisata.getText().toString() + nomor_transaksi);
                        reference3.getRef().child( "nama_wisata" ).setValue(nama_wisata.getText().toString());
                        reference3.getRef().child( "lokasi" ).setValue(lokasi.getText().toString() );
                        reference3.getRef().child( "ketentuan" ).setValue(ketentuan.getText().toString());
                        reference3.getRef().child( "jumlah_tiket" ).setValue(ValueJumlahTiket.toString() );
                        reference3.getRef().child( "date_wisata" ).setValue(date_wisata );
                        reference3.getRef().child( "time_wisata" ).setValue(time_wisata );
                        Intent gotosucsesticket = new Intent( TicketCheckoutAct.this, SuccesBuyTicketAct.class );
                        startActivity( gotosucsesticket );
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );

                // Update data balance kepada user (yg saat ini login)
                // mengambil data user dari firebase
                reference4 = FirebaseDatabase.getInstance().getReference().child( "Users" ).child( username_key_new);
                reference4.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sisa_balance = MyBalance - ValueTotalHarga;
                        reference4.getRef().child( "user_ballance" ).setValue(sisa_balance );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }
        } );

        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoback = new Intent( TicketCheckoutAct.this, TiketDetailAct.class );
                startActivity( gotoback );
            }
        } );
    }



    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences( USERNAME_KEY, MODE_PRIVATE );
        username_key_new = sharedPreferences.getString( username_key, "" );

    }
}
