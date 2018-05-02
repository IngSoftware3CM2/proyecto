package com.is.controlincidencias.converter;

import java.time.LocalDate;

public class StringToLocalDate {

    private StringToLocalDate() {
    }

    public static LocalDate tryParseDate(String text){
        LocalDate date=null;
        try{
            int mes = Integer.parseInt(text.substring(0, text.indexOf('/')));
            int dia = Integer.parseInt(text.substring(text.indexOf('/')+1,text.lastIndexOf('/')));
            int anio = Integer.parseInt(text.substring(text.lastIndexOf('/')+1,text.length()));
            date=LocalDate.of(anio, mes, dia);
            return date;
        }catch(Exception e){
            return null;
        }
    }
}

