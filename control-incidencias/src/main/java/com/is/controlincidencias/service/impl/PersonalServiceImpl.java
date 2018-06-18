package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.repository.PersonalRepository;
import com.is.controlincidencias.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personalServiceImpl")
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    @Qualifier("personalRepository")
    private PersonalRepository personalRepository;

    @Override
    public Personal getPersonalByIdEmpleado(int idEmpleado) {
        return personalRepository.findByIdEmpleado(idEmpleado);
    }

    @Override
    public Personal getPersonalByEmail(String email) {
        return personalRepository.findByLogin_Correo(email);
    }

    @Override
    public Personal getPersonalByEmailAndPwd(String email, String pwd) {
        return personalRepository.findByLogin_CorreoAndLogin_Passwordhash(email, pwd);
    }

    @Override
    public void actualizarContra(Personal personal) {
        personalRepository.save(personal);
    }

    @Override
    public List<Personal> getPersonalByTipo(String tipo) {
        if(tipo.equals("AMBOS")){
            return personalRepository.findAllByAmbosTipos("ROLE_DOC", "ROLE_PAAE");
        }
        return personalRepository.findAllByTipo(tipo);
    }
}
