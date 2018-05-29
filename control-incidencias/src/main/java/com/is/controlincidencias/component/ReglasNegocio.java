package com.is.controlincidencias.component;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("reglasNegocioComponent")
public class ReglasNegocio {
    private ReglasNegocio(){
    }

    public boolean rn12(LocalDate inicio, LocalDate actual){
        LocalDate fechamin = actual.minus(15,ChronoUnit.DAYS);
        return (inicio.isAfter(actual) || inicio.isBefore(fechamin));
    }

    public boolean rn14(LocalDate inicio,LocalDate fin){
        LocalDate fechamax = inicio.plus(2,ChronoUnit.MONTHS);
        return fin.isAfter(fechamax);
    }
    public boolean rn15(String folio){
        Pattern p;
        Matcher m;
        // comprueba que no contenga caracteres prohibidos
        p = Pattern.compile("[^A-Z0-9]");
        m = p.matcher(folio);
        return m.find();
    }

    public boolean rn28(LocalDate inicio, LocalDate fin){
        return inicio.isAfter(fin);
    }

}
