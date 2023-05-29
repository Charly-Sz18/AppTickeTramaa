package com.example.proyectofinal.models;

public class CarteleraEvento {
    private int id ;
    private String imagen ;
    private String nombreEvento;
    private String artista;
    private String fecha;
    private String hora;
    private String descripcion;
    private String duracion;
    private String genero;

    public CarteleraEvento(int id, String imagen, String nombreEvento, String artista, String fecha, String hora, String descripcion, String duracion,String genero) {
        this.id = id;
        this.imagen = imagen;
        this.nombreEvento = nombreEvento;
        this.artista = artista;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "CarteleraEvento{" +
                "id=" + id +
                ", imagen='" + imagen + '\'' +
                ", nombreEvento='" + nombreEvento + '\'' +
                ", artista='" + artista + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                '}';
    }
}
