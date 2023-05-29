package com.example.proyectofinal.models;

import java.time.Period;
import java.util.List;

public class ArraySingleton {
    private static  ArraySingleton instance;

    private List<asientoEstatico> asientosModificados;
    private List<Asiento> asientosservidor;

    private List<PreciosZona> listaPrecios;

    public static  ArraySingleton getInstance(){
        if(instance == null){
            instance= new ArraySingleton();
        }
        return instance;
    }

    public List<PreciosZona> getListaPrecios() {
        return listaPrecios;
    }

    public void setListaPrecios(List<PreciosZona> listaPrecios) {
        this.listaPrecios = listaPrecios;
    }

    public List<asientoEstatico> getAsientosModificados() {
        return asientosModificados;
    }

    public List<Asiento> getAsientosservidor() {
        return asientosservidor;
    }

    public void setAsientosModificados(List<asientoEstatico> asientosModificados) {
        this.asientosModificados = asientosModificados;
    }

    public void setAsientosservidor(List<Asiento> asientosservidor) {
        this.asientosservidor = asientosservidor;
    }
}
