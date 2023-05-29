package com.example.proyectofinal.models;

public class PreciosZona {

    private int evento_id;
    private int precio_id;
    private double precio;
    private int zona_id;
    private String zona;

    public PreciosZona(int evento_id, int precio_id, double precio, int zona_id, String zona) {
        this.evento_id = evento_id;
        this.precio_id = precio_id;
        this.precio = precio;
        this.zona_id = zona_id;
        this.zona = zona;
    }

    public int getEvento_id() {
        return evento_id;
    }

    public void setEvento_id(int evento_id) {
        this.evento_id = evento_id;
    }

    public int getPrecio_id() {
        return precio_id;
    }

    public void setPrecio_id(int precio_id) {
        this.precio_id = precio_id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getZona_id() {
        return zona_id;
    }

    public void setZona_id(int zona_id) {
        this.zona_id = zona_id;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "PreciosZona{" +
                "evento_id=" + evento_id +
                ", precio_id=" + precio_id +
                ", precio=" + precio +
                ", zona_id=" + zona_id +
                ", zona='" + zona + '\'' +
                '}';
    }
}
