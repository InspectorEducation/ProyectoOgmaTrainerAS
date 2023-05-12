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
    private int maquina_seleccionada;

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaDeMaquinas();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_maquinas);

        Bundle extras = getIntent().getExtras();
        maquina_seleccionada = extras.getInt("codigo_maquina");
        System.out.println("MAQUINA SELECCIONADA");
        System.out.println(maquina_seleccionada);

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
        refrescarListaDeMaquinas();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad EditarMaquinaActivity.java
                Maquina maquinaSeleccionada = listaDeMaquinas.get(position);
                Intent intent = new Intent(CargarMaquinasActivity.this, EditarMaquinaActivity.class);
                intent.putExtra("idMaquina", maquinaSeleccionada.getId());
                intent.putExtra("nombreMaquina", maquinaSeleccionada.getNombre());
                intent.putExtra("descripcionMaquina", maquinaSeleccionada.getDescripcion());
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
                Intent intent = new Intent(CargarMaquinasActivity.this, AgregarMaquinaActivity.class);
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



    public void refrescarListaDeMaquinas() {
        /*
         * ==========
         * Justo aquí obtenemos la lista de la BD
         * y se la ponemos al RecyclerView
         * ============
         *
         * */
        if (adaptadorMaquinas == null) return;
        Toast.makeText(CargarMaquinasActivity.this, "Se refresco", Toast.LENGTH_SHORT).show();
        listaDeMaquinas = maquinaController.obtenerMaquinas();
        adaptadorMaquinas.setListaDeMaquinas(listaDeMaquinas);
        adaptadorMaquinas.notifyDataSetChanged();
    }
}
