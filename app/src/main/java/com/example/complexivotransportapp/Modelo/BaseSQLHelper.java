package com.example.complexivotransportapp.Modelo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    public String insertaPersona(Persona persona) {
        String SQLi = "";
        SQLi += "insert into Persona (cedulaPersona,nombrePersona,apellidoPersona,correoPersona,celularPersona,usuario,contrasenia,imagenPersona)";
        SQLi += " values (";
        SQLi += "'" + persona.getCedula_persona() + "'";
        SQLi += ",'" + persona.getNombre_persona() + "'";
        SQLi += ",'" + persona.getApellido_persona() + "'";
        SQLi += ",'" + persona.getCorreo_persona() + "'";
        SQLi += ",'" + persona.getCelular_persona() + "'";
        SQLi += ",'" + persona.getUsuario() + "'";
        SQLi += ",'" + persona.getContrasenia() + "'";
        SQLi += ",'" + persona.getImagen() + "'";
        SQLi += ")";
        try {
            this.getWritableDatabase().execSQL(SQLi);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;
    }

    //Login



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
