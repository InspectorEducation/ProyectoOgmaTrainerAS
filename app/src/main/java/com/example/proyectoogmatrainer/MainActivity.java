package com.example.proyectoogmatrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectoogmatrainer.controllers.MaquinaController;
import com.example.proyectoogmatrainer.modelos.Maquina;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Maquina> listaDeMaquinas;
    private RecyclerView recyclerView;
    private AdaptadorMaquinas adaptadorMaquinas;
    private MaquinaController maquinaController;
    private FloatingActionButton fabAgregarMaquina;

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaDeMaquinas();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maquinaController = new MaquinaController(MainActivity.this);

        recyclerView = findViewById(R.id.recyclerViewMaquina);
        fabAgregarMaquina = findViewById(R.id.fabAgregarMaquina);

        listaDeMaquinas = new ArrayList<>();
        adaptadorMaquinas = new AdaptadorMaquinas(listaDeMaquinas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorMaquinas);

        // Una vez que ya configuramos el RecyclerView le ponemos los datos de la BD
        refrescarListaDeMaquinas();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad EditarMaquinaActivity.java
                Maquina maquinaSeleccionada = listaDeMaquinas.get(position);
                Intent intent = new Intent(MainActivity.this, EditarMaquinaActivity.class);
                intent.putExtra("idMaquina", maquinaSeleccionada.getId());
                intent.putExtra("nombreMaquina", maquinaSeleccionada.getNombre());
                intent.putExtra("descripcionMaquina", maquinaSeleccionada.getDescripcion());
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Maquina maquinaParaEliminar = listaDeMaquinas.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                maquinaController.eliminarMaquinas(maquinaParaEliminar);
                                refrescarListaDeMaquinas();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar a la maquina " + maquinaParaEliminar.getNombre() + "?")
                        .create();
                dialog.show();

            }
        }));

        // Listener del FAB
        fabAgregarMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplemente cambiamos de actividad
                Intent intent = new Intent(MainActivity.this, AgregarMaquinaActivity.class);
                startActivity(intent);
            }
        });
    }
        public void refrescarListaDeMaquinas() {
            /*
             * ==========
             * Justo aquí obtenemos la lista de la BD
             * y se la ponemos al RecyclerView
             * ============
             *
             * */
            if (adaptadorMaquinas == null) return;
            Toast.makeText(MainActivity.this, "Se refresco", Toast.LENGTH_SHORT).show();
            listaDeMaquinas = maquinaController.obtenerMaquinas();
            adaptadorMaquinas.setListaDeMaquinas(listaDeMaquinas);
            System.out.println("MAQUINAS BD");
            System.out.println(listaDeMaquinas.get(0).getNombre());
            adaptadorMaquinas.notifyDataSetChanged();
        }
}