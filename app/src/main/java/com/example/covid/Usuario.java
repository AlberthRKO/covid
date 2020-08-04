package com.example.covid;

public class Usuario {
    private int idUsuario;
    private String ci;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String contrasena;
    private String rol;
    private String estado;
    private String ejeX;
    private String ejeY;
    private String activo;

    public Usuario(int idUsuario, String ci, String nombres, String apellidos, String telefono, String correo, String contrasena, String rol, String estado, String ejeX, String ejeY, String activo) {
        this.idUsuario = idUsuario;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = estado;
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getCi() {
        return ci;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    public String getEstado() {
        return estado;
    }

    public String getEjeX() {
        return ejeX;
    }

    public String getEjeY() {
        return ejeY;
    }

    public String getActivo() {
        return activo;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEjeX(String ejeX) {
        this.ejeX = ejeX;
    }

    public void setEjeY(String ejeY) {
        this.ejeY = ejeY;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
