package com.example.proyectofinal.models;

public class IdSingleton {
    private static IdSingleton instance;
    int id;
    String nombre;

    String fecha;
    String hora;
    String URLImagen;

    private IdSingleton(){

    }

    public static IdSingleton getInstance(){
        if(instance == null){
            instance = new IdSingleton();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getURLImagen() {
        return URLImagen;
    }

    public void setURLImagen(String URLImagen) {
        this.URLImagen = URLImagen;
    }
}
