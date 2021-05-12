package com.example.complexivotransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.complexivotransportapp.Modelo.BaseSQLHelper;
import com.example.complexivotransportapp.Modelo.Persona;

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
//a
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = Persona.logeo(getApplicationContext(), txtUsuario.getText().toString(), txtContrasenia.getText().toString());
                if(cursor.getCount()>0){
                    Toast.makeText(getApplicationContext(), "Siiiiu", Toast.LENGTH_LONG).show();
                    Intent intent  =new Intent(getApplicationContext(),Listar.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Nooou", Toast.LENGTH_LONG).show();
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