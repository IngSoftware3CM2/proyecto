package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Notificacion;
import com.is.controlincidencias.entity.Personal;

public interface NotificacionService {
    boolean existsByPersonal (Personal personal);
    Notificacion findByPersonal (Personal personal);
}
