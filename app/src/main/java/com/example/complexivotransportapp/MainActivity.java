package com.example.complexivotransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.complexivotransportapp.Modelo.BaseSQLHelper;

public class MainActivity extends AppCompatActivity {
    private TextView txtUsuario;
    private TextView txtContrasenia;
    private Button ingresar;
    private TextView crearCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario=findViewById(R.id.txtUsuario);
        txtContrasenia=findViewById(R.id.txtContrasenia);
        ingresar=findViewById(R.id.btnIngresar);
        crearCuenta=findViewById(R.id.crearCuenta);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario=txtUsuario.getText().toString();
                String contrasenia=txtContrasenia.getText().toString();
                BaseSQLHelper superHelper=new BaseSQLHelper(getApplicationContext());
                if(superHelper.listaPersonas(usuario,contrasenia)==true){
                    Toast.makeText(getApplicationContext(),"Login True",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"no mame",Toast.LENGTH_LONG).show();
                }
            }
        });
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(getApplicationContext(),registroUsuarios.class);
                startActivity(intent);
                finish();
            }
        });

    }


}