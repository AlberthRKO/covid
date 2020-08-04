package com.example.covid;

public class Ubicacion {
    private int idUbicacion;
    private String nombre;
    private String descripcion;
    private String gravedad;
    private String ejeX;
    private String ejeY;
    private String activo;

    public Ubicacion(int idUbicacion, String nombre, String descripcion, String gravedad, String ejeX, String ejeY, String activo) {
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.gravedad = gravedad;
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.activo = activo;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
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

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public String getEjeX() {
        return ejeX;
    }

    public void setEjeX(String ejeX) {
        this.ejeX = ejeX;
    }

    public String getEjeY() {
        return ejeY;
    }

    public void setEjeY(String ejeY) {
        this.ejeY = ejeY;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
