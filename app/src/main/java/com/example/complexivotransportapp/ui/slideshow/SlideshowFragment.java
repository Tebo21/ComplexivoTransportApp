package com.example.complexivotransportapp.ui.slideshow;

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

import com.example.complexivotransportapp.Modelo.Vehiculo;
import com.example.complexivotransportapp.R;
import com.example.complexivotransportapp.VehiculoServicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    ListView listView;
    ArrayList<String> cars = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        listView=root.findViewById(R.id.listViewVehiculos);
        arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,cars);
        listView.setAdapter(arrayAdapter);
        getVehiculoWs();

        return root;

    }

    private void getVehiculoWs(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://parallelum.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VehiculoServicio carService=retrofit.create(VehiculoServicio.class);
        Call<List<Vehiculo>> call=carService.getVehiculo();
        call.enqueue(new Callback<List<Vehiculo>>() {
            @Override
            public void onResponse(Call<List<Vehiculo>> call, Response<List<Vehiculo>> response) {
                for(Vehiculo vec:response.body()){
                    cars.add("Codigo Vehiculo: "+vec.getCodigo()+"\nMarca Vehiculo: "+vec.getNome());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Vehiculo>> call, Throwable t) {

            }
        });
    }
}