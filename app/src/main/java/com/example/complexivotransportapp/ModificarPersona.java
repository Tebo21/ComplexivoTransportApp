package com.example.complexivotransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.complexivotransportapp.Modelo.BaseSQLHelper;
import com.example.complexivotransportapp.Modelo.Persona;
import com.example.complexivotransportapp.ui.gallery.GalleryFragment;

public class ModificarPersona extends AppCompatActivity {
    private EditText modificaCedula;
    private EditText modificaImagen;
    private EditText modificaNombre;
    private EditText modificaApellido;
    private EditText modificaCelular;
    private EditText modificaCorreo;
    private EditText modificaUsuario;
    private EditText modificaContrasenia;
    private Button butGuardar;
    private Button butCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_persona);

        //Casting de elementos>
        modificaCedula=(EditText)findViewById(R.id.modificaCedula);
        modificaImagen=(EditText)findViewById(R.id.modificaImagen);
        modificaNombre=(EditText)findViewById(R.id.modificaNombre);
        modificaApellido=(EditText)findViewById(R.id.modificaApellido);
        modificaCelular=(EditText)findViewById(R.id.modificaCelular);
        modificaCorreo=(EditText)findViewById(R.id.modificaCorreo);
        modificaUsuario=(EditText)findViewById(R.id.modificaUsuario);
        modificaContrasenia=(EditText)findViewById(R.id.modificaContrasenia);
        butGuardar=(Button)findViewById(R.id.buttonGuardar);
        butCancelar=(Button)findViewById(R.id.buttonCancelar);
        butGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseSQLHelper superHelper=new BaseSQLHelper(getApplicationContext());
                Persona persona=new Persona(modificaCedula.getText().toString(),modificaNombre.getText().toString(),modificaApellido.getText().toString(),modificaCorreo.getText().toString(),modificaCelular.getText().toString(),modificaUsuario.getText().toString(),modificaContrasenia.getText().toString(),modificaImagen.getText().toString());
                String sentencia=superHelper.ModificaPer(persona);
                if(sentencia==null){
                    Toast.makeText(getApplicationContext(),"Modificación Exitosa",Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent  =new Intent(getApplicationContext(),Listar.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR " +sentencia,Toast.LENGTH_LONG).show();
                }
            }
        });

//entraPersona();
/*
        butGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseSQLHelper superHelper=new BaseSQLHelper(getApplicationContext());
                Persona pe=new Persona(modificaCedula.getText().toString(),modificaImagen.getText().toString(),modificaNombre.getText().toString(),modificaApellido.getText().toString(),modificaCelular.getText().toString(),modificaCorreo.getText().toString(),modificaUsuario.getText().toString(),modificaContrasenia.getText().toString());
                Cursor cursor=pe.modificaPersona(getApplicationContext(),pe);

                    Toast.makeText(getApplicationContext(), "Usuario Actualizado", Toast.LENGTH_LONG).show();
                    Intent intent  =new Intent(getApplicationContext(),Listar.class);
                    startActivity(intent);
                    finish();

            }
        });
        */

    }


    public void entraPersona(){
        GalleryFragment gf= new GalleryFragment();
        Persona per2=new Persona();
        Cursor cursor=per2.getModificado(getApplicationContext(),gf.cedulaPersona);
        System.out.println("1"+cursor.getColumnIndex("cedulaPersona")+"2"+cursor.getColumnIndexOrThrow("cedulaPersona"));
        System.out.println(cursor.getString(1));
        }
}