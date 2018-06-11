package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.PersonalQuincena;
import com.is.controlincidencias.repository.PersonalQuincenaRepository;
import com.is.controlincidencias.service.PersonalQuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("personalQuincenaServiceImpl")
public class PersonalQuincenaServiceImpl implements PersonalQuincenaService {

    @Autowired
    @Qualifier("personalQuincenaRepository")
    private PersonalQuincenaRepository personalQuincenaRepository;

    @Override
    public Integer preguntarPorDiasSuplementariosQuincena(int idempleado, int idquincena) {
        return personalQuincenaRepository.preguntarPorDiasSuplementariosQuincena(idempleado,idquincena);
    }

    @Override
    public Integer selectMaxIdPersonalQuincena() {
        return personalQuincenaRepository.selectMaxIdPersonalQuincena();
    }

    @Override
    public void insertRegistroSupl(int idpersonalquincena,int tiemposuplsolicitados, int idempleado, int idquincena) {
        personalQuincenaRepository.insertRegistroSupl(idpersonalquincena,tiemposuplsolicitados,idempleado,idquincena);
    }

    @Override
    public void updateSuplementarioQuincena(int tiempossuplsolicitados, int idempleado, int idquincena) {
        personalQuincenaRepository.updateSuplementarioQuincena(tiempossuplsolicitados,idempleado,idquincena);
    }

    @Override
    public PersonalQuincena findAllByPersonalQuincena(int idempleado, int idquincena) {
        return personalQuincenaRepository.findAllByPersonalQuincena(idempleado,idquincena);
    }
}
