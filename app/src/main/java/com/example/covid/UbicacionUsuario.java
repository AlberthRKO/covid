package com.example.covid;

public class UbicacionUsuario {
    private int idUbicacionUsuario;
    private String fecha;
    private String activo;
    private int idUsuario;
    private int idUbicacion;

    public UbicacionUsuario(int idUbicacionUsuario, String fecha, String activo, int idUsuario, int idUbicacion) {
        this.idUbicacionUsuario = idUbicacionUsuario;
        this.fecha = fecha;
        this.activo = activo;
        this.idUsuario = idUsuario;
        this.idUbicacion = idUbicacion;
    }

    public int getIdUbicacionUsuario() {
        return idUbicacionUsuario;
    }

    public void setIdUbicacionUsuario(int idUbicacionUsuario) {
        this.idUbicacionUsuario = idUbicacionUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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
