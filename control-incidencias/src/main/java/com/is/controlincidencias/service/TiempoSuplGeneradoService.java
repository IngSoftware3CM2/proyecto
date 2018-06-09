package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.TiempoSuplGenerado;
import java.util.List;

public interface TiempoSuplGeneradoService{
    List<TiempoSuplGenerado> findByPersonal(Personal personal);
}
