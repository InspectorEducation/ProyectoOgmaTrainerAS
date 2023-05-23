package com.example.proyectoogmatrainer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoogmatrainer.controllers.MaquinaController;
import com.example.proyectoogmatrainer.modelos.Maquina;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CargarMaquinasActivity extends AppCompatActivity {

    private List<Maquina> listaDeMaquinas;
    private RecyclerView recyclerView;
    private AdaptadorMaquinas adaptadorMaquinas;
    private MaquinaController maquinaController;
    private FloatingActionButton fabAgregarMaquina;
    private int MAQUINA_SELECCIONADA;

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        MAQUINA_SELECCIONADA = extras.getInt("codigo_maquina");
        refrescarListaDeReservasPorMaquina(MAQUINA_SELECCIONADA);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_maquinas);

        Bundle extras = getIntent().getExtras();
        MAQUINA_SELECCIONADA = extras.getInt("codigo_maquina");
        maquinaController = new MaquinaController(CargarMaquinasActivity.this);

        recyclerView = findViewById(R.id.recyclerViewMaquina);
        fabAgregarMaquina = findViewById(R.id.fabAgregarMaquina);

        listaDeMaquinas = new ArrayList<>();
        adaptadorMaquinas = new AdaptadorMaquinas(listaDeMaquinas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorMaquinas);

        // Una vez que ya configuramos el RecyclerView le ponemos los datos de la BD
        refrescarListaDeReservasPorMaquina(MAQUINA_SELECCIONADA);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad EditarMaquinaActivity.java
                Maquina maquinaSeleccionada = listaDeMaquinas.get(position);
                Intent intent = new Intent(CargarMaquinasActivity.this, EditarReservaActivity.class);
                intent.putExtra("idMaquina", maquinaSeleccionada.getId());
                intent.putExtra("nombreMaquina", maquinaSeleccionada.getNombre());
                intent.putExtra("descripcionMaquina", maquinaSeleccionada.getDescripcion());
                intent.putExtra("id_maquina",maquinaSeleccionada.getId_maquina());
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Maquina maquinaParaEliminar = listaDeMaquinas.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(CargarMaquinasActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                maquinaController.eliminarMaquinas(maquinaParaEliminar);
                                refrescarListaDeReservasPorMaquina(MAQUINA_SELECCIONADA);
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
                Intent intent = new Intent(CargarMaquinasActivity.this, AgregarReservaActivity.class);
                intent.putExtra("codigo_maquina", MAQUINA_SELECCIONADA);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.list_maquinas:
                setContentView(R.layout.view_lista_maquinas);
                finish();
                break;
            case R.id.rutinas:
                Intent intent=new Intent(CargarMaquinasActivity.this,RutinaActivity.class);
                startActivity(intent);
                //finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refrescarListaDeReservasPorMaquina(int id) {
        if (adaptadorMaquinas == null) return;
        Toast.makeText(CargarMaquinasActivity.this, "Se refresco por id "+id, Toast.LENGTH_SHORT).show();
        listaDeMaquinas = maquinaController.obtenerReservasPorID(id);
        for (Maquina maquina: listaDeMaquinas
             ) {
            maquina.toString();
        }
        adaptadorMaquinas.setListaDeMaquinas(listaDeMaquinas);
        adaptadorMaquinas.notifyDataSetChanged();
    }
}
