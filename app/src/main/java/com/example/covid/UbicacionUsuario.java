package com.example.covid;

public class UbicacionUsuario {
    private int idUbicacionUsuario;
    private String nombre;
    private String fecha;
    private int idUsuario;
    private int idUbicacion;

    public UbicacionUsuario(int idUbicacionUsuario, String zona, String fecha, int idUsuario, int idUbicacion) {
        this.idUbicacionUsuario = idUbicacionUsuario;
        this.nombre = nombre;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idUbicacion = idUbicacion;
    }

    public int getIdUbicacionUsuario() {
        return idUbicacionUsuario;
    }

    public void setIdUbicacionUsuario(int idUbicacionUsuario) {
        this.idUbicacionUsuario = idUbicacionUsuario;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
}
