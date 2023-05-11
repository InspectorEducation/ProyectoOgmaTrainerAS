package com.example.proyectoogmatrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoogmatrainer.controllers.MaquinaController;
import com.example.proyectoogmatrainer.modelos.Maquina;

public class EditarMaquinaActivity extends AppCompatActivity {
    private EditText etEditarNombre, etEditarDescripcion;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Maquina maquina;//La maquina que vamos a estar editando
    private MaquinaController maquinaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_maquina);

        // Recuperar datos que enviaron
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        // Instanciar el controlador de las maquinas
        maquinaController = new MaquinaController(EditarMaquinaActivity.this);

        // Rearmar la maquina
        // Nota: igualmente solamente podríamos mandar el id y recuperar la maquina de la BD
        long idMaquina = extras.getLong("idMaquina");
        String nombreMaquina = extras.getString("nombreMaquina");
        String descripcionMaquina = extras.getString("descripcionMaquina");
        maquina = new Maquina(nombreMaquina, descripcionMaquina, "2023-05-11",idMaquina );


        // Ahora declaramos las vistas
        etEditarDescripcion = findViewById(R.id.etEditarDescripcion);
        etEditarNombre = findViewById(R.id.etEditarNombre);
        btnCancelarEdicion = findViewById(R.id.btnCancelarEdicionMaquina);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambiosMaquina);


        // Rellenar los EditText con los datos de la maquinas
        etEditarDescripcion.setText(String.valueOf(maquina.getDescripcion()));
        etEditarNombre.setText(maquina.getNombre());

        // Listener del click del botón para salir, simplemente cierra la actividad
        btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del click del botón que guarda cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                etEditarNombre.setError(null);
                etEditarDescripcion.setError(null);
                // Crear la maquinas con los nuevos cambios pero ponerle
                // el id de la anterior
                String nuevoNombre = etEditarNombre.getText().toString();
                String posibleNuevaDescri = etEditarDescripcion.getText().toString();
                if (nuevoNombre.isEmpty()) {
                    etEditarNombre.setError("Escribe el nombre");
                    etEditarNombre.requestFocus();
                    return;
                }
                if (posibleNuevaDescri.isEmpty()) {
                    etEditarDescripcion.setError("Escribe la edad");
                    etEditarDescripcion.requestFocus();
                    return;
                }
                // Si no es entero, igualmente marcar error
                String nuevaDescri;
                try {
                    nuevaDescri = posibleNuevaDescri;
                } catch (NumberFormatException e) {
                    etEditarDescripcion.setError("Escribe un número");
                    etEditarDescripcion.requestFocus();
                    return;
                }
                // Si llegamos hasta aquí es porque los datos ya están validados
                Maquina maquinaConNuevosCambios = new Maquina(nuevoNombre, nuevaDescri,"2023-05-11" ,maquina.getId());
                int filasModificadas = maquinaController.guardarCambios(maquinaConNuevosCambios);
                if (filasModificadas != 1) {
                    // De alguna forma ocurrió un error porque se debió modificar únicamente una fila
                    Toast.makeText(EditarMaquinaActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    finish();
                }
            }
        });
    }
}
