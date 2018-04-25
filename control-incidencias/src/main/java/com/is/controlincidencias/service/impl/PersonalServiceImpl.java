package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.repository.PersonalRepository;
import com.is.controlincidencias.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("personalServiceImpl")
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    @Qualifier("personalRepository")
    private PersonalRepository personalRepository;

    @Override
    public Personal getPersonalByNoEmpleado(int noEmpleado) {
        return personalRepository.findByNoEmpleado(noEmpleado);
    }
}
