package com.example.proyectoogmatrainer.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyectoogmatrainer.AyudanteBaseDeDatos;
import com.example.proyectoogmatrainer.modelos.Maquina;

import java.util.ArrayList;

public class MaquinaController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "maquinas";

    public MaquinaController(Context contexto) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public int eliminarMaquinas(Maquina maquina) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(maquina.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaMaquina(Maquina maquina) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", maquina.getNombre());
        valoresParaInsertar.put("descripcion", maquina.getDescripcion());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Maquina maquinaEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", maquinaEditada.getNombre());
        valoresParaActualizar.put("descripcion", maquinaEditada.getDescripcion());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idMaquina
        String[] argumentosParaActualizar = {String.valueOf(maquinaEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Maquina> obtenerMaquinas() {
        ArrayList<Maquina> maquinas = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, descripcion, id
        String[] columnasAConsultar = {"nombre", "descripcion", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from maquinas
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return maquinas;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return maquinas;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de maquinas
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreObtenidoDeBD = cursor.getString(0);
            String descripcionObtenidaDeBD = cursor.getString(1);
            long idMaquina = cursor.getLong(2);
            Maquina maquinaObtenidaDeBD = new Maquina(nombreObtenidoDeBD, descripcionObtenidaDeBD, idMaquina);
            maquinas.add(maquinaObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de maquinas :)
        cursor.close();
        return maquinas;
    }

}

