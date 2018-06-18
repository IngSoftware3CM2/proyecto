package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.PersonalPeriodoInhabil;

public interface PersonalPeriodoInhabilService {
    int savePersonalSinPeriodo(PersonalPeriodoInhabil personalPeriodoInhabil);
    Integer getMaxId();
}
