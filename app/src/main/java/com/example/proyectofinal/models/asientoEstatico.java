package com.example.proyectofinal.models;

public class asientoEstatico {

    private int id;
    private String fila;
    private int columna;
    private String seleccionado;

    private int id_zona;

    public asientoEstatico(String fila, int columna, int id_zona,String seleccionado,int id) {
        this.fila = fila;
        this.columna = columna;
        this.id_zona= id_zona;
        this.seleccionado= seleccionado;
        this.id= id;
    }

    public asientoEstatico(String fila, int columna, int id_zona,int id) {
        this.fila = fila;
        this.columna = columna;
        this.id_zona= id_zona;
        this.seleccionado= "1";
        this.id= id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_zona() {
        return id_zona;
    }

    public String getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String estaSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public String toString() {
        return "asientoEstatico{" +
                "id=" + id +
                ", fila='" + fila + '\'' +
                ", columna=" + columna +
                ", seleccionado='" + seleccionado + '\'' +
                ", id_zona=" + id_zona +
                '}';
    }
}
