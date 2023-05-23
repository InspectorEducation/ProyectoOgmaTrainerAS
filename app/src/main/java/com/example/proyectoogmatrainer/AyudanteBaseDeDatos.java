package com.example.proyectoogmatrainer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper{

    private static final String NOMBRE_BASE_DE_DATOS = "maquinas",
            NOMBRE_TABLA_MAQUINA = "maquinas";
    private static final int VERSION_BASE_DE_DATOS = 1;


    public AyudanteBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s(id integer primary key autoincrement, nombre text, id_maquina integer, descripcion text, fechaReserva text)", NOMBRE_TABLA_MAQUINA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
