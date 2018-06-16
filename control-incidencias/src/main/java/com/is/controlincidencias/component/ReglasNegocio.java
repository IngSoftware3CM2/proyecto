package com.is.controlincidencias.component;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("reglasNegocioComponent")
public class ReglasNegocio {

    private static final Log LOG = LogFactory.getLog(ReglasNegocio.class);
    private ReglasNegocio(){
    }

    public boolean rn1(String input){
        return (input.trim().length()!=0) ;
    }

    public boolean rn2(LocalDate fecha){
        Pattern p;
        Matcher m;
        // comprueba que no contenga caracteres prohibidos
        p = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}");
        m = p.matcher(fecha.toString());
        return m.find();
    }
    public boolean rn12(LocalDate inicio, LocalDate actual){
        LocalDate fechamin = actual.minus(15,ChronoUnit.DAYS);
        return (inicio.isAfter(actual) || inicio.isBefore(fechamin));
    }

    public boolean rn14(LocalDate inicio,LocalDate fin){
        LocalDate fechamax = inicio.plus(1,ChronoUnit.MONTHS);
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

    public boolean rn21(String newPassword){
        Pattern p;
        Matcher m;
        p = Pattern.compile("[^a-zA-Z0-9]");
        m = p.matcher(newPassword);
        if(newPassword.length()<8 || newPassword.length()>30)
            return true;
        return m.find();
    }

    public boolean rn28(LocalDate inicio, LocalDate fin){
        return inicio.isAfter(fin);
    }

    public boolean rn29(Integer dias){
        if(dias >= 3){
            return false;
        }else{
            return true;
        }
    }

    public boolean rn31(LocalTime horas,Integer tiempoSolicitado){
        return horas.minus(tiempoSolicitado,ChronoUnit.HOURS).equals(LocalTime.parse("00:00:00"));
    }

    public boolean rn54(DayOfWeek dow){
        LOG.info("***********************************************************");
        LOG.info(dow);
        String compara=dow.toString();
     if(compara.equals("SUNDAY") || compara.equals("SATURDAY")){
         LOG.info("Estoy en el if");
         return true;
     }
     return false;
    }
}
