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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.lista:
                Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
                startActivity(intent);
                break;
            case R.id.list_maquinas:
                setContentView(R.layout.view_lista_maquinas);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
            Intent intent = new Intent(MainActivity.this, CargarMaquinasActivity.class);
            startActivity(intent);
        } else {
            // Las credenciales son incorrectas
            Toast.makeText(getApplicationContext(), "Credenciales incorrectas, por favor intente de nuevo", Toast.LENGTH_SHORT).show();
        }
    }
}