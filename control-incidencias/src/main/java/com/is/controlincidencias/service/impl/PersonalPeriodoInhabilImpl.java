package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.PersonalPeriodoInhabil;
import com.is.controlincidencias.repository.PersonalPeriodoInhabilRepository;
import com.is.controlincidencias.service.PersonalPeriodoInhabilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("personalPeriodoInhabilServiceImpl")
public class PersonalPeriodoInhabilImpl implements PersonalPeriodoInhabilService {
    @Autowired
    @Qualifier("personalPeriodoInhabilRepository")
    PersonalPeriodoInhabilRepository personalPeriodoInhabilRepository;

    @Override
    public int savePersonalSinPeriodo(PersonalPeriodoInhabil personalPeriodoInhabil) {
        personalPeriodoInhabilRepository.save(personalPeriodoInhabil);
        return 1;
    }

    @Override
    public Integer getMaxId() {
        return personalPeriodoInhabilRepository.selectMaxIdPersonalPeriodoInhabil();
    }
}
