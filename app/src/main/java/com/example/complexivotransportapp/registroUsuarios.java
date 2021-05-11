package com.example.complexivotransportapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.complexivotransportapp.Modelo.BaseSQLHelper;
import com.example.complexivotransportapp.Modelo.Persona;

public class registroUsuarios extends AppCompatActivity {

    private EditText editTextCedulaPersona;
    private EditText editTextNombrePersona;
    private EditText editTextApellidoPersona;
    private EditText editTextCelularPersona;
    private EditText editTextCorreoPersona;
    private EditText editTextUsuario;
    private EditText editTextContrasenia;
    private EditText editTextImagen;
    private ImageView imagen;

    private Button buttonGuardarPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        editTextCedulaPersona=(EditText)findViewById(R.id.editTextCedulaPersona);
        editTextNombrePersona=(EditText)findViewById(R.id.editTextNombrePersona);
        editTextApellidoPersona=(EditText)findViewById(R.id.editTextApellidoPersona);
        editTextCelularPersona=(EditText)findViewById(R.id.editTextCelularPersona);
        editTextCorreoPersona=(EditText)findViewById(R.id.editTextCorreoPersona);
        editTextUsuario=(EditText)findViewById(R.id.editTextUsuario);
        editTextContrasenia=(EditText)findViewById(R.id.editTextContrasenia);
        editTextImagen=(EditText)findViewById(R.id.editTextImagen);

        imagen=(ImageView)findViewById(R.id.imageButton);

        buttonGuardarPersona=(Button)findViewById(R.id.buttonGuardarPersona);

        buttonGuardarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarCampos();
            }
        });
    }

        public boolean ValidarCampos(){
            boolean retorno = true;
            String ced = editTextCedulaPersona.getText().toString();
            String name = editTextNombrePersona.getText().toString();
            String ape = editTextApellidoPersona.getText().toString();
            String cel = editTextCelularPersona.getText().toString();
            String corr = editTextCorreoPersona.getText().toString();
            String usu = editTextUsuario.getText().toString();
            String ctr = editTextContrasenia.getText().toString();
            String ima = editTextImagen.getText().toString();

            if (ced.isEmpty()) {
                editTextCedulaPersona.setError("Campo vacio");
                retorno = false;
            }
            if (name.isEmpty()) {
                editTextNombrePersona.setError("Campo vacio");
                retorno = false;
            }
            if (ape.isEmpty()) {
                editTextApellidoPersona.setError("Campo vacio");
                retorno = false;
            }
            if (cel.isEmpty()) {
                editTextCelularPersona.setError("Campo vacio");
                retorno = false;
            }
            if (corr.isEmpty()) {
                editTextCorreoPersona.setError("Campo vacio");
                retorno = false;
            }
            if (usu.isEmpty()) {
                editTextUsuario.setError("Campo vacio");
                retorno = false;
            }
            if (ctr.isEmpty()) {
                editTextContrasenia.setError("Campo vacio");
                retorno = false;
            }
            if (ima.isEmpty()) {
                editTextImagen.setError("Campo vacio");
                retorno = false;
            }


            if (!ced.isEmpty() &&!name.isEmpty() && !ape.isEmpty() &&!cel.isEmpty() && !corr.isEmpty() && !usu.isEmpty() && !ctr.isEmpty()) {
                BaseSQLHelper superHelper=new BaseSQLHelper(getApplicationContext());
                Persona persona =new Persona(editTextCedulaPersona.getText().toString(),editTextNombrePersona.getText().toString(), editTextApellidoPersona.getText().toString(),editTextCelularPersona.getText().toString(),editTextCorreoPersona.getText().toString(),editTextUsuario.getText().toString(),editTextContrasenia.getText().toString(),editTextImagen.getText().toString());
                String sentencia=superHelper.insertaPersona(persona);
                if(sentencia==null){
                    Toast.makeText(getApplicationContext(),"Registro Exitoso",Toast.LENGTH_LONG).show();
                    Intent intent  =new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR " +sentencia,Toast.LENGTH_LONG).show();
                }
            }
            return retorno;
        }
    public void onclick(View view) {
        cargarImagen();

    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            Uri pathlocal = data.getData();
            imagen.setImageURI(pathlocal);
        }
    }

}