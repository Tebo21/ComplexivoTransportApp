package com.example.complexivotransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.complexivotransportapp.Modelo.Horario;
import com.example.complexivotransportapp.Modelo.Persona;

public class ListadoFinal extends AppCompatActivity {
    private ListView listadoFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_final);

        listadoFinal=(ListView)findViewById((R.id.listViewPersona));
        String[] from =new String[]{"horaInicio"};
        int[] hasta = new int[]{android.R.id.text1};
        Cursor cursor = Horario.getCursor(getApplicationContext());
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                cursor,from,hasta,0
        );
        listadoFinal = (ListView)findViewById(R.id.listViewPersona);
        listadoFinal.setAdapter(cursorAdapter);

    }
}