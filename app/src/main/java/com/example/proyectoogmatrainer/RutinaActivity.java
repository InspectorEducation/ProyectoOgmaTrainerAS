package com.example.proyectoogmatrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RutinaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina);
        Button verMasBotonpecho= findViewById(R.id.botonpecho);
        Button verMasButtonbicep = findViewById(R.id.botonbicep);
        Button verMasButtonpierna = findViewById(R.id.botonpierna);
        Button verMasButtonabdomen = findViewById(R.id.botonabdomen);

        verMasButtonbicep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_layaout_img2);
            }
        });

        verMasBotonpecho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_layaout_img1);
            }
        });

        verMasButtonpierna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_layaout_img4);
            }
        });

        verMasButtonabdomen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_layaout_img3);
            }
        });

    }
}
