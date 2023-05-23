package com.example.proyectoogmatrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoogmatrainer.controllers.MaquinaController;
import com.example.proyectoogmatrainer.modelos.Maquina;

public class AgregarMaquinaActivity extends AppCompatActivity {
    private Button btnAgregarMaquina, btnCancelarNuevaMaquina;
    private EditText etNombre;
    private Spinner etDescripcion;
    private DatePicker dtFechaReserva;
    private MaquinaController maquinaController;
    private Integer codigo_maquina;

    private String maquina_seleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_maquina);

        Bundle extras = getIntent().getExtras();
        codigo_maquina = extras.getInt("codigo_maquina");

        // Instanciar vistas
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        dtFechaReserva = findViewById(R.id.datePicker);
        btnAgregarMaquina = findViewById(R.id.btnAgregarMaquina);
        btnCancelarNuevaMaquina = findViewById(R.id.btnCancelarNuevaMaquina);

        //llenar el menu de maquinas
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.maquinas_disponibles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        etDescripcion.setAdapter(adapter);

        // Crear el controlador
        maquinaController = new MaquinaController(AgregarMaquinaActivity.this);

        //Iteam seleccionado
        etDescripcion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Obtener la máquina seleccionada
                maquina_seleccionada = (String) adapterView.getItemAtPosition(position);
                System.out.println("MAQUINA SELECCIONA ON ITEM");
                System.out.println(maquina_seleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Agregar listener del botón de guardar
        btnAgregarMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                etNombre.setError(null);
                String nombre = etNombre.getText().toString();
                int year = dtFechaReserva.getYear();
                int month = dtFechaReserva.getMonth();
                int dayOfMonth = dtFechaReserva.getDayOfMonth();

                String fechaComoCadena = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", dayOfMonth);
                System.out.println(fechaComoCadena); // Salida: 2023-05-11

                if ("".equals(nombre)) {
                    etNombre.setError("Escribe el nombre de la maquina");
                    etNombre.requestFocus();
                    return;
                }

                // Ya pasó la validación
                Maquina nuevaMaquina = new Maquina(nombre, maquina_seleccionada,fechaComoCadena,codigo_maquina);
                System.out.println("NUEVA MAQUINA TO STRING");
                System.out.println(nuevaMaquina.toString());
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
