package com.example.complexivotransportapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.complexivotransportapp.HorarioService;
import com.example.complexivotransportapp.Modelo.BaseSQLHelper;
import com.example.complexivotransportapp.Modelo.Horario;
import com.example.complexivotransportapp.Modelo.Vehiculo;
import com.example.complexivotransportapp.R;
import com.example.complexivotransportapp.VehiculoServicio;
import com.example.complexivotransportapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    ListView listView;
    ArrayList<String> horarios = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView=root.findViewById(R.id.listViewHorarios);
        arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,horarios);
        listView.setAdapter(arrayAdapter);
        getHorariosWs();

        return root;
    }

    private void getHorariosWs(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HorarioService horService=retrofit.create(HorarioService.class);
        Call<List<Horario>> call=horService.getHorarios();
        BaseSQLHelper superHelper= new BaseSQLHelper(getContext());
        call.enqueue(new Callback<List<Horario>>() {
            @Override
            public void onResponse(Call<List<Horario>> call, Response<List<Horario>> response) {
                for(Horario hor:response.body()){
                    horarios.add("Hora de Inicio: "+hor.getHoraInicio()+"\nHora de Salida: "+hor.getHoraFin());
                    //SE VA GUARDANDO EN LA BASE
                    superHelper.guardarHorario(hor);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Horario>> call, Throwable t) {

            }
        });
    }
}