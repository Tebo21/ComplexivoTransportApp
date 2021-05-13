package com.example.complexivotransportapp;


import com.example.complexivotransportapp.Modelo.Horario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HorarioService {

    String RUTA_API="/Horario/listar-horario";
    @GET(RUTA_API)
    Call<List<Horario>> getHorarios();
}
