package com.example.tiket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
        Context context;
        ArrayList<Myticket> myTicket;

        public TicketAdapter (Context c, ArrayList<Myticket> p){
            context = c;
            myTicket = p;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder( LayoutInflater.from( context ).inflate( R.layout.item_myticket, viewGroup, false ) );
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
            myViewHolder.xnama_wisata.setText( myTicket.get( position ).getNama_wisata() );
            myViewHolder.xlokasi.setText( myTicket.get( position ).getLokasi() );
            myViewHolder.xjumlah_tiket.setText( myTicket.get( position ).getJumlah_tiket() + " Tickets" );
            final String getNamaWisata = myTicket.get( position ).getNama_wisata();

            myViewHolder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gotomyticketdetail = new Intent( context, MyTicketsDetail.class );
                    gotomyticketdetail.putExtra( "nama_wisata", getNamaWisata );
                    context.startActivity( gotomyticketdetail );
                }
            } );

        }

        @Override
        public int getItemCount() {
            return myTicket.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView xnama_wisata, xlokasi, xjumlah_tiket;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            xnama_wisata = itemView.findViewById( R.id.xnama_wisata );
            xlokasi = itemView.findViewById( R.id.xlokasi );
            xjumlah_tiket = itemView.findViewById( R.id.xjumlah_tiket );
        }
    }
}
