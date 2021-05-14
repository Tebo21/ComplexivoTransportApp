package com.example.complexivotransportapp.ui.listadoWs;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.complexivotransportapp.ListadoFinal;
import com.example.complexivotransportapp.Modelo.Horario;
import com.example.complexivotransportapp.ModificarPersona;
import com.example.complexivotransportapp.R;
import com.example.complexivotransportapp.registroUsuarios;

public class ListadoWS extends Fragment {

    private ListadoWViewModel mViewModel;
    private ListView listViewServicio;
//

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mViewModel=
                new ViewModelProvider(this).get(ListadoWViewModel.class);
        View root = inflater.inflate(R.layout.fragment_web, container, false);

        ListView listView=root.findViewById((R.id.listViewServicio));
        String[] from =new String[]{"horaInicio"};
        int[] hasta = new int[]{android.R.id.text1};
        Cursor cursor = Horario.getCursor(getContext());
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                getContext(), android.R.layout.simple_list_item_1,
                cursor,from,hasta,0
        );
        listViewServicio = (ListView) root.findViewById(R.id.listViewServicio);
        listView.setAdapter(cursorAdapter);


        return root;

    }



}