package com.example.complexivotransportapp.Modelo;

public class Persona {
    private String cedula_persona;
    private String nombre_persona;
    private String apellido_persona;
    private String celular_persona;
    private String correo_persona;
    private String usuario;
    private String contrasenia;
    private String imagen;

    public Persona(String cedula_persona, String nombre_persona, String apellido_persona, String celular_persona, String correo_persona, String usuario, String contrasenia, String imagen) {
        this.cedula_persona = cedula_persona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.celular_persona = celular_persona;
        this.correo_persona = correo_persona;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.imagen = imagen;
    }

    public String getCedula_persona() {
        return cedula_persona;
    }

    public void setCedula_persona(String cedula_persona) {
        this.cedula_persona = cedula_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellido_persona() {
        return apellido_persona;
    }

    public void setApellido_persona(String apellido_persona) {
        this.apellido_persona = apellido_persona;
    }

    public String getCelular_persona() {
        return celular_persona;
    }

    public void setCelular_persona(String celular_persona) {
        this.celular_persona = celular_persona;
    }

    public String getCorreo_persona() {
        return correo_persona;
    }

    public void setCorreo_persona(String correo_persona) {
        this.correo_persona = correo_persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
