package com.example.proyectofinal.models;

public class Asiento {

    private int id;
    private String fila;
    private int columna;
    private String disponibilidad;
    private int zona_teatro_id;
    private int evento_teatro_id;
    private int status;

    public Asiento(int id, String fila, int columna, String disponibilidad, int zona_teatro_id, int evento_teatro_id, int status) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.disponibilidad = disponibilidad;
        this.zona_teatro_id = zona_teatro_id;
        this.evento_teatro_id = evento_teatro_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getZona_teatro_id() {
        return zona_teatro_id;
    }

    public void setZona_teatro_id(int zona_teatro_id) {
        this.zona_teatro_id = zona_teatro_id;
    }

    public int getEvento_teatro_id() {
        return evento_teatro_id;
    }

    public void setEvento_teatro_id(int evento_teatro_id) {
        this.evento_teatro_id = evento_teatro_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "id=" + id +
                ", fila='" + fila + '\'' +
                ", columna=" + columna +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", zona_teatro_id=" + zona_teatro_id +
                ", evento_teatro_id=" + evento_teatro_id +
                ", status=" + status +
                '}';
    }
}
