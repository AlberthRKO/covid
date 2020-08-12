package com.example.covid;

public class Hospital {
    private int idHospital;
    private String nombre;
    private String ejeX;
    private String ejeY;
    private String descripcion;
    private String activo;

    public Hospital(int idHospital, String nombre, String ejeX, String ejeY, String descripcion, String activo) {
        this.idHospital = idHospital;
        this.nombre = nombre;
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
