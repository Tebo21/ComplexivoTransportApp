package com.example.complexivotransportapp.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseSQLHelper extends SQLiteOpenHelper {


    private static final String DATABASE = "movil.db";
    Context miContext;


    public BaseSQLHelper(Context context) {
        super(context, DATABASE, null, 1);
        miContext = context;
        File pathArchivo = miContext.getDatabasePath(DATABASE);
        //VERIFICAR ARCHIVO
        if (!verificaBase(pathArchivo.getAbsolutePath())) {
            //COPIAR ARCHIVO
            try {
                copiarBase(pathArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void noQuery(String sql){
        this.getWritableDatabase().execSQL(sql);
    }

    public Cursor query(String sql){
        return this.getReadableDatabase().rawQuery(sql, null);
    }

    public String ModificaPer(Persona persona) {
        String SQLi = "";
        SQLi += "UPDATE Persona SET nombrePersona =" + " '" + persona.getNombrePersona()
                + "' ,apellidoPersona='" + persona.getApellidoPersona()
                + "' ,correoPersona='" + persona.getCorreoPersona()
                + "' ,celularPersona='" + persona.getCelularPersona()
                + "',usuario='" + persona.getUsuario()
                + "' ,contrasenia='" + persona.getContrasenia()
                + "' ,imagenPersona='" + persona.getImagenPersona()
                + "' WHERE cedulaPersona = '" + persona.getCedulaPersona() + "'";

        try {
            this.getWritableDatabase().execSQL(SQLi);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;


    }

    public String insertaPersona(Persona persona) {
        String SQLi = "";
        SQLi += "insert into Persona (cedulaPersona,nombrePersona,apellidoPersona,correoPersona,celularPersona,usuario,contrasenia,imagenPersona)";
        SQLi += " values (";
        SQLi += "'" + persona.getCedulaPersona() + "'";
        SQLi += ",'" + persona.getNombrePersona() + "'";
        SQLi += ",'" + persona.getApellidoPersona() + "'";
        SQLi += ",'" + persona.getCorreoPersona() + "'";
        SQLi += ",'" + persona.getCelularPersona() + "'";
        SQLi += ",'" + persona.getUsuario() + "'";
        SQLi += ",'" + persona.getContrasenia() + "'";
        SQLi += ",'" + persona.getImagenPersona() + "'";
        SQLi += ")";
        try {
            this.getWritableDatabase().execSQL(SQLi);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;
    }
    public String guardarHorario(Horario horario){
        String SQLi = "";
        SQLi += "insert into Horario (idHorario,horaInicio,horaFin)";
        SQLi += " values (";
        SQLi += "'" + horario.getIdHorario() + "'";
        SQLi += ",'" + horario.getHoraInicio() + "'";
        SQLi += ",'" + horario.getHoraFin() + "'";
        SQLi += ")";
        try {
            this.getWritableDatabase().execSQL(SQLi);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;
    }



    //ELIMINA PERSONA
    public String eliminaPersona(String cedulaPersona) {
        String SQLi = "";
        SQLi += "delete from Persona ";
        SQLi += " where cedulaPersona='" + cedulaPersona + "'";
        try {
            this.getWritableDatabase().execSQL(SQLi);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;


    }

    //RECUPERAR PERSONAS
    public Cursor listaPersonas() {
        Cursor cursor;
        String SQLC = "select ROWID as _id,* from Persona";
        cursor = this.getReadableDatabase().rawQuery(SQLC, null);
        return cursor;
    }

    //VERIFICAR ARCHIVO
    private boolean verificaBase(String ruta) {
        SQLiteDatabase miBase = null;
        try {
            miBase = SQLiteDatabase.openDatabase(ruta, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception ex) {

        }
        if (miBase != null) {
            miBase.close();
        }

        return miBase != null;
    }

    //COPIAR BASE
    private void copiarBase(File rutaBase) throws IOException {
        InputStream miInput = miContext.getAssets().open(DATABASE);
        OutputStream miOutput = new FileOutputStream(rutaBase);
        byte[] buffer = new byte[1024];
        int largo;
        while ((largo = miInput.read(buffer)) > 0) {
            miOutput.write(buffer, 0, largo);
        }
        miOutput.flush();
        miOutput.close();
        miInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
