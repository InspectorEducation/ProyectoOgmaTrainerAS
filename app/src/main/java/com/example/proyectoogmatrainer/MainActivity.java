package com.example.proyectoogmatrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText mUsuarioEditText;
    private EditText mPasswordEditText;
    private RadioButton mAceptoRadioButton;
    private Button mIngresarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //setTheme(R.style.Theme_ProyectSplashAndMenu);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Cargada", Toast.LENGTH_LONG).show();

        mUsuarioEditText = findViewById(R.id.eTNombre);
        mPasswordEditText = findViewById(R.id.eTPassoword);
        mAceptoRadioButton = findViewById(R.id.rBTerminos);
        mIngresarButton = findViewById(R.id.bIngresar);

        mIngresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCredenciales();
            }
        });
    }

    private void validarCredenciales() {
        // Obtener los valores de usuario y contraseña ingresados
        String usuario = mUsuarioEditText.getText().toString().trim();
        String contrasena = mPasswordEditText.getText().toString().trim();

        // Validar si los campos están vacíos
        if (TextUtils.isEmpty(usuario)) {
            mUsuarioEditText.setError("Por favor ingrese su usuario");
            mUsuarioEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(contrasena)) {
            mPasswordEditText.setError("Por favor ingrese su contraseña");
            mPasswordEditText.requestFocus();
            return;
        }

        // Validar las credenciales del usuario
        if (usuario.equals("Admin") && contrasena.equals("123")) {
            // Las credenciales son correctas, se puede iniciar sesión
            Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.view_lista_maquinas);
        } else {
            // Las credenciales son incorrectas
            Toast.makeText(getApplicationContext(), "Credenciales incorrectas, por favor intente de nuevo", Toast.LENGTH_SHORT).show();
        }
    }

    public void onMaquinaSeleccionada(View view) {
        Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
        intent.putExtra("codigo_maquina",102);
        startActivity(intent);
    }

    public void onBarraDominacion(View view) {
        Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
        intent.putExtra("codigo_maquina",101);
        startActivity(intent);
    }

    public void onBandaElastica(View view) {
        Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
        intent.putExtra("codigo_maquina",103);
        startActivity(intent);
    }

    public void onBicicleta(View view) {
        Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
        intent.putExtra("codigo_maquina",104);
        startActivity(intent);
    }

    public void onCintaCorrer(View view) {
        Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
        intent.putExtra("codigo_maquina",105);
        startActivity(intent);
    }

    public void onPrensaPiernas(View view) {
        Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
        intent.putExtra("codigo_maquina",107);
        startActivity(intent);
    }

}