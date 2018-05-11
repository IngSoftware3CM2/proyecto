package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DocenteForm extends JefeForm{
    private boolean abierto;
    private int horas;
    private List<DiaForm> dias = new ArrayList<>();

    public void addDia(DiaForm dia) {
        dias.add(dia);
    }


}
