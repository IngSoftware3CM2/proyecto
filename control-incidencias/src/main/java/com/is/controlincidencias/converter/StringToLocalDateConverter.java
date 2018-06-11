package com.is.controlincidencias.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component("stringToLocalDateConverter")
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
