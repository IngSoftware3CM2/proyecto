package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Notificacion;
import com.is.controlincidencias.entity.Personal;

public interface NotificacionService {
    boolean existsByPersonal (Personal personal);
    boolean existsByidempleado (int id);
    Notificacion findByidempleado (int id);
    Notificacion findByPersonal (Personal personal);
    void removeByPersonalAndMotivo(int idPersonal, int idMotivo);
}
