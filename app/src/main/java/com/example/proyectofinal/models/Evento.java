package com.example.proyectofinal.models;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;
    private String foto;
    private String titulo;

    public Evento(int id, String foto, String titulo) {
        this.id = id;
        this.foto = foto;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getFoto() {
        return foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "evento{" +
                "id=" + id +
                ", foto='" + foto + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
