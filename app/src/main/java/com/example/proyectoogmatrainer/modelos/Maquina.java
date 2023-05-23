package com.example.proyectoogmatrainer.modelos;

public class Maquina {
    private long id;
    private int id_maquina;
    private String nombre;
    private String descripcion;

    private String fechaReserva;

    public Maquina(String nombre, String descripcion, String fechaReserva, int id_maquina) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaReserva = fechaReserva;
        this.id_maquina = id_maquina;
    }

    public Maquina(String nombre, String descripcion,String fechaReserva,long id,int id_maquina) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaReserva = fechaReserva;
        this.id_maquina = id_maquina;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(int id_maquina) {
        this.id_maquina = id_maquina;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "id=" + id +
                ", id_maquina=" + id_maquina +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaReserva='" + fechaReserva + '\'' +
                '}';
    }
}
