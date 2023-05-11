package com.example.proyectoogmatrainer.modelos;

import androidx.annotation.NonNull;

public class Cliente {

    private String nombre;
    private String apellido;
    private String documento;
    private String password;
    private int edad;
    private String telefono;
    private String email;
    private String genero;
    private String peso;
    private String altura;
    private String historial_medico;

    public Cliente(String nombre, String apellido, String documento, String password, int edad, String telefono, String email, String genero, String peso, String altura, String historial_medico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.password = password;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.historial_medico = historial_medico;
    }

    public Cliente(String nombre, String apellido, String documento, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getHistorial_medico() {
        return historial_medico;
    }

    public void setHistorial_medico(String historial_medico) {
        this.historial_medico = historial_medico;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", documento=" + documento +
                '}';
    }
}
