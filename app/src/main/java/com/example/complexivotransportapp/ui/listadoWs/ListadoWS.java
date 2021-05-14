package com.example.complexivotransportapp.ui.listadoWs;

import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.complexivotransportapp.Modelo.Horario;
import com.example.complexivotransportapp.Modelo.Persona;
import com.example.complexivotransportapp.R;
import com.example.complexivotransportapp.ui.gallery.GalleryViewModel;

public class ListadoWS extends Fragment {

    private ListadoWViewModel mViewModel;
    private ListView listViewServicio;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel=new ViewModelProvider(this).get(ListadoWViewModel.class);
        View root = inflater.inflate(R.layout.listado_w_fragment, container, false);
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

        return inflater.inflate(R.layout.listado_w_fragment, container, false);
    }


}