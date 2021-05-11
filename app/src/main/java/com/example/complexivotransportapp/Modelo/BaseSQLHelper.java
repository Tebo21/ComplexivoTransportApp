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

    //RECUPERAR PERSONAS
    public boolean listaPersonas(String usuario,String contrasenia) {
        Cursor cursor;
        Persona pe= new Persona();
        String SQLC = "select ROWID as _id,* from Persona";
        cursor = this.getReadableDatabase().rawQuery(SQLC, null);
        List<Persona> lp=new ArrayList<>();
        for(int i=0;i<cursor.getCount();i++){
            pe.setUsuario(cursor.getColumnName(6));
            pe.setContrasenia(cursor.getColumnName(7));
            lp.add(pe);
            if(lp.get(i).getUsuario().equals(usuario) && lp.get(i).getContrasenia().equals(contrasenia)){
                return true;
            }
            return false;
        }
        return false;
    }

    //Login
    public void login(String usuario, String contrasenia) {
/*
            SQLiteDatabase db=this.getReadableDatabase();
            String[] parametros={usuario,contrasenia};
            try {

                Cursor cursor=db.rawQuery("SELECT "+Persona.usuario+","+Persona.getContrasenia+
                        " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"= ? or "+Utilidades.CAMPO_NOMBRE+"=?  ",parametros);
                cursor.moveToFirst();
                System.out.println("cursor"+cursor);
                if(cursor.getCount()>0){
                    Toast.makeText(null,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    return true;
                }else {
                    Toast.makeText(null,"Registro Exitoso not",Toast.LENGTH_LONG).show();
                    return false;
                }

            }catch (Exception e){
                db.close();
                return false;
            }
*/
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
