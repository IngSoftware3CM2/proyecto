package com.is.controlincidencias.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component("stringToLocalTimeConverter")
public class StringToLocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String s) {
        return LocalTime.parse(s, DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
