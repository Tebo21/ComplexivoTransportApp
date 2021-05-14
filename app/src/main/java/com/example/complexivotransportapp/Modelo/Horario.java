package com.example.complexivotransportapp.Modelo;

import android.content.Context;
import android.database.Cursor;

public class Horario {

    private long idHorario;
    private String horaInicio;
    private String horaFin;

    public Horario() {
    }

    public long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(long idHorario) {
        this.idHorario = idHorario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public static Cursor getCursor(Context context){
        BaseSQLHelper baseSQLHelper = new BaseSQLHelper(context);
        String sql="select _rowid_ as _id, * from Horario";
        return  baseSQLHelper.query(sql);
    }



}
