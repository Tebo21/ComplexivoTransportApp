package com.example.complexivotransportapp.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.complexivotransportapp.Listar;
import com.example.complexivotransportapp.Modelo.Persona;
import com.example.complexivotransportapp.ModificarPersona;
import com.example.complexivotransportapp.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private ListView listViewPersona;
    private com.example.complexivotransportapp.Modelo.BaseSQLHelper superHelper;
    public String cedulaPersona="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ListView listView=root.findViewById((R.id.listViewPersona));
        String[] from =new String[]{"usuario"};
        int[] hasta = new int[]{android.R.id.text1};
        Cursor cursor = Persona.getCursor(getContext());
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                getContext(), android.R.layout.simple_list_item_1,
                cursor,from,hasta,0
        );
        listViewPersona = (ListView) root.findViewById(R.id.listViewPersona);
        listView.setAdapter(cursorAdapter);


        listViewPersona.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                alerta
                        .setIcon(R.drawable.logo)
                        .setTitle("Eliminar")

                        .setMessage("Atención: \nElimina un Registro")
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cursor itemp = (Cursor) listViewPersona.getItemAtPosition(position);
                                cedulaPersona= itemp.getString(1);
                                Persona pe=new Persona();
                                Cursor cursor=pe.eliminaPersona(getContext(),cedulaPersona);
                                pe.eliminaPersona(getContext(),cedulaPersona);
                                if(cursor.getCount()<cursorAdapter.getCount()){
                                    Toast.makeText(getContext(), "Usuario Eliminado", Toast.LENGTH_LONG).show();
                                    Intent intent  =new Intent(getContext(),Listar.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }else {
                                    Toast.makeText(getContext(), "Error al Eliminar", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog = alerta.show();
                return false;
            }
        });


        listViewPersona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), ModificarPersona.class);
                startActivity(intent);

            }
        });


        return root;



    }



}