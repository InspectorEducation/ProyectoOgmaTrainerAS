package com.example.proyectoogmatrainer.modelos;

public class Maquina {
    private long id;
    private String nombre;
    private String descripcion;

    private String fechaReserva;

    public Maquina(String nombre, String descripcion, String fechaReserva) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaReserva = fechaReserva;
    }

    public Maquina(String nombre, String descripcion,String fechaReserva,long id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaReserva = fechaReserva;
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

    @Override
    public String toString() {
        return "Maquina{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
