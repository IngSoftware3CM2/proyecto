package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Notificacion;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.repository.NotificacionRepository;
import com.is.controlincidencias.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("notificacionServiceImpl")
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    @Qualifier("notificacionRepository")
    private NotificacionRepository notificacionRepository;

    @Override
    public boolean existsByPersonal(Personal personal) {
        return notificacionRepository.existsByPersonal(personal);
    }

    @Override
    public Notificacion findByPersonal(Personal personal) {
        return notificacionRepository.findByPersonal(personal);
    }

    @Override
    public boolean existsByidempleado(int id) {
        return notificacionRepository.existsByPersonal_IdEmpleado(id);
    }

    @Override
    public Notificacion findByidempleado(int id) {
        return notificacionRepository.findByPersonal_IdEmpleado(id);
    }
}
