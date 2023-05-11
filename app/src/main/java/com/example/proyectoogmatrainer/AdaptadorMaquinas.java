package com.example.proyectoogmatrainer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoogmatrainer.modelos.Maquina;

import java.util.List;

public class AdaptadorMaquinas extends RecyclerView.Adapter<AdaptadorMaquinas.MyViewHolder>{

    private List<Maquina> listaDeMaquinas;

    public void setListaDeMaquinas(List<Maquina> listaDeMaquinas) {
        this.listaDeMaquinas = listaDeMaquinas;
    }

    public AdaptadorMaquinas(List<Maquina> listaDeMaquinas) {
        this.listaDeMaquinas = listaDeMaquinas;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaMaquina = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_maquinas, viewGroup, false);
        return new MyViewHolder(filaMaquina);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Obtener la maquina de nuestra lista gracias al índice i
        Maquina maquina = listaDeMaquinas.get(i);
        // Obtener los datos de la lista
        String nombreMaquina = maquina.getNombre();
        String descripcionMaquina = maquina.getDescripcion();
        String fechaReservaMaquina = maquina.getFechaReserva();
        // Y poner a los TextView los datos con setText
        myViewHolder.nombre.setText(nombreMaquina);
        myViewHolder.descripcion.setText(descripcionMaquina);
        myViewHolder.fechaReserva.setText(fechaReservaMaquina);
    }

    @Override
    public int getItemCount() {
        return listaDeMaquinas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, fechaReserva;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvNombre);
            this.descripcion = itemView.findViewById(R.id.tvDescripcion);
            this.fechaReserva= itemView.findViewById(R.id.tvFechaReserva);
        }
    }
}
