package com.is.controlincidencias.model;


import java.util.ArrayList;
import java.util.List;

public class DocenteForm extends JefeForm{
    private boolean abierto;
    private int horas;
    private List<DiaForm> dias = new ArrayList<>();

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public int getHoras() {
        return horas;
    }

    public void addDia(DiaForm dia) {
        dias.add(dia);
    }

    public List<DiaForm> getDias() {
        return dias;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }


}
