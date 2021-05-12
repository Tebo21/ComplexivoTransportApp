package com.example.complexivotransportapp;

import com.example.complexivotransportapp.Modelo.Vehiculo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VehiculoServicio {
    String RUTA_API="/fipe/api/v1/carros/marcas";
    @GET(RUTA_API)
    Call<List<Vehiculo>> getVehiculo();
}
