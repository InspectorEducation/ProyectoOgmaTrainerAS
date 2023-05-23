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
        valoresParaInsertar.put("fechaReserva",maquina.getFechaReserva());
        valoresParaInsertar.put("id_maquina",maquina.getId_maquina());

        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Maquina maquinaEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", maquinaEditada.getNombre());
        valoresParaActualizar.put("descripcion", maquinaEditada.getDescripcion());
        valoresParaActualizar.put("fechaReserva",maquinaEditada.getFechaReserva());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idMaquina
        String[] argumentosParaActualizar = {String.valueOf(maquinaEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Maquina> obtenerReservasPorID(int id_maquina) {
        ArrayList<Maquina> maquinas = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, descripcion, id
        String[] columnasAConsultar = {"nombre", "descripcion","fechaReserva","id","id_maquina"};
        String seleccion = "id_maquina = ?";
        String[] seleccionArgs = {String.valueOf(id_maquina)};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from maquinas
                columnasAConsultar,
                seleccion,
                seleccionArgs,
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
            // nombre, descripcioon,id entonces el nombre es 0, descripcion 1 e fecha es 2, id es 3, id_maquina es 4
            String nombreObtenidoDeBD = cursor.getString(0);
            String descripcionObtenidaDeBD = cursor.getString(1);
            String fechaReservaObtenidaDeBd = cursor.getString(2);
            long id_reserva = cursor.getInt(3);

            Maquina maquinaObtenidaDeBD = new Maquina(nombreObtenidoDeBD, descripcionObtenidaDeBD, fechaReservaObtenidaDeBd,id_reserva,id_maquina);
            maquinas.add(maquinaObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de maquinas :)
        cursor.close();
        return maquinas;
    }

}

