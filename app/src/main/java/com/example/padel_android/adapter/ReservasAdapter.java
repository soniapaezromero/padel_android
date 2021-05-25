package com.example.padel_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.padel_android.R;
import com.example.padel_android.models.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.MyViewHolder> {
    private  Context context;
    public List<Reserva> listaReservas;
    public ReservasAdapter(){
        this.listaReservas = new ArrayList<>();
    }
    public ReservasAdapter(Context context){

    }
    public ReservasAdapter(Context context, List<Reserva> reservaPista) {
        this.context = context;
        this.listaReservas = reservaPista;
    }


    public Reserva getPaseoAt(int position) {
        return listaReservas.get(position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView codigo,fecha,horaStart,horaEnd;



        public MyViewHolder(View itemview) {
            super(itemview);
            this.codigo=itemview.findViewById(R.id.reserva);
            this.fecha = itemview.findViewById(R.id.fecha);
            this.horaStart=itemview.findViewById(R.id.comienzo);
            this.horaEnd=itemview.findViewById(R.id.fin);



        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(listaReservas !=null) {
            Reserva reserva = listaReservas.get(position);
            holder.codigo.setText(String.valueOf(reserva.getId()));
            holder.fecha.setText(reserva.getFecha().toString());
            holder.horaStart.setText(reserva.getHoraComienzo().toString());
            holder.horaEnd.setText(reserva.getHoraFin().toString());

        }else{
            holder.codigo.setText("No hay ninguna reserva");
        }

    }

    @Override
    public int getItemCount() {
        if(listaReservas != null) {
            return listaReservas.size();
        }else{
            return 0;
        }
    }
    public void setReservas(List<Reserva> reservas){
        listaReservas =reservas;
        notifyDataSetChanged();
    }

    public Reserva getAt(int position){
        Reserva reserva;
        reserva = this. listaReservas.get(position);
        return reserva;
    }

    public void add(Reserva reserva) {
        this. listaReservas.add(reserva);
        notifyItemInserted( listaReservas.size() - 1);
        notifyItemRangeChanged(0,  listaReservas.size() - 1);
    }

    public void modifyAt(Reserva reserva, int position) {
        this. listaReservas.set(position, reserva);
        notifyItemChanged(position);
    }

    public void removeAt(int position) {
        this. listaReservas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0,  listaReservas.size() - 1);
    }


}