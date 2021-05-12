package com.example.complexivotransportapp.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class Persona {
    private String cedulaPersona;
    private String nombrePersona;
    private String apellidoPersona;
    private String correoPersona;
    private String celularPersona;
    private String usuario;
    private String contrasenia;
    private String imagenPersona;

    public Persona() {
    }

    public Persona(String cedulaPersona, String nombrePersona, String apellidoPersona, String correoPersona, String celularPersona, String usuario, String contrasenia, String imagenPersona) {
        this.cedulaPersona = cedulaPersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.correoPersona = correoPersona;
        this.celularPersona = celularPersona;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.imagenPersona = imagenPersona;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
    }

    public String getCelularPersona() {
        return celularPersona;
    }

    public void setCelularPersona(String celularPersona) {
        this.celularPersona = celularPersona;
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

    public String getImagenPersona() {
        return imagenPersona;
    }

    public void setImagenPersona(String imagenPersona) {
        this.imagenPersona = imagenPersona;
    }

    public static Cursor logeo(Context context, String usuario, String contrasenia){
        BaseSQLHelper baseSQLHelper = new BaseSQLHelper(context);
        String sql="SELECT * FROM Persona p WHERE p.usuario = '"+usuario+"' and p.contrasenia = '"+contrasenia+"';";
        return baseSQLHelper.query(sql);
    }

    public static Cursor getCursor(Context context){
        BaseSQLHelper baseSQLHelper = new BaseSQLHelper(context);
        String sql="select _rowid_ as _id, * from Persona";
        return  baseSQLHelper.query(sql);
    }

    public static Cursor eliminaPersona(Context context,String cedulaPersona) {
        BaseSQLHelper baseSQLHelper = new BaseSQLHelper(context);
        String SQLi = "";
        SQLi += "delete from Persona ";
        SQLi += " where cedulaPersona='" + cedulaPersona + "'";
        return baseSQLHelper.query(SQLi);
    }
    public static Cursor modificaPersona(Context context,Persona persona) {
        BaseSQLHelper baseSQLHelper = new BaseSQLHelper(context);
        String SQLi = "";
        SQLi += "UPDATE Persona SET nombrePersona =" + " '" + persona.getNombrePersona()
                + "' ,apellidoPersona='" + persona.getApellidoPersona()
                + "' ,correoPersona='" + persona.getCorreoPersona()
                + "' ,celularPersona='" + persona.getCelularPersona()
                + "',usuario='" + persona.getUsuario()
                + "' ,contrasenia='" + persona.getContrasenia()
                + "' ,imagenPersona='" + persona.getImagenPersona()
                + "' WHERE cedulaPersona = '" + persona.getCedulaPersona() + "'";
        return baseSQLHelper.query(SQLi);
    }

    public static Cursor getModificado(Context context,String cedulaPersona){
        BaseSQLHelper baseSQLHelper = new BaseSQLHelper(context);
        String sql="select _rowid_ as _id, * from Persona WHERE cedulaPersona ='"+cedulaPersona+"'";
        return  baseSQLHelper.query(sql);
    }


}
