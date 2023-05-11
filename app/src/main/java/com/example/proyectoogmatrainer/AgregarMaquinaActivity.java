package com.example.proyectoogmatrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoogmatrainer.controllers.MaquinaController;
import com.example.proyectoogmatrainer.modelos.Maquina;

public class AgregarMaquinaActivity extends AppCompatActivity {
    private Button btnAgregarMaquina, btnCancelarNuevaMaquina;
    private EditText etNombre, etDescripcion;
    private DatePicker dtFechaReserva;
    private MaquinaController maquinaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_maquina);

        // Instanciar vistas
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        dtFechaReserva = findViewById(R.id.datePicker);
        btnAgregarMaquina = findViewById(R.id.btnAgregarMaquina);
        btnCancelarNuevaMaquina = findViewById(R.id.btnCancelarNuevaMaquina);
        // Crear el controlador
        maquinaController = new MaquinaController(AgregarMaquinaActivity.this);

        // Agregar listener del botón de guardar
        btnAgregarMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                etNombre.setError(null);
                etDescripcion.setError(null);
                String nombre = etNombre.getText().toString(),
                        descripcionComoCadena = etDescripcion.getText().toString();
                if ("".equals(nombre)) {
                    etNombre.setError("Escribe el nombre de la maquina");
                    etNombre.requestFocus();
                    return;
                }
                if ("".equals(descripcionComoCadena)) {
                    etDescripcion.setError("Escribe la descripcion de la maquina");
                    etDescripcion.requestFocus();
                    return;
                }

                // Ver si es un entero
                String descripcion = etDescripcion.getText().toString();
                // Ya pasó la validación
                Maquina nuevaMaquina = new Maquina(nombre, descripcion);
                long id = maquinaController.nuevaMaquina(nuevaMaquina);
                if (id == -1) {
                    // De alguna manera ocurrió un error
                    Toast.makeText(AgregarMaquinaActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    // Terminar
                    finish();
                }
            }
        });

        // El de cancelar simplemente cierra la actividad
        btnCancelarNuevaMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
