package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.TiempoSuplGenerado;
import com.is.controlincidencias.repository.TiempoSuplGeneradoRepository;
import com.is.controlincidencias.service.TiempoSuplGeneradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("tiempoSuplGeneradoServiceImpl")
public class TiempoSuplGeneradoServiceImpl implements TiempoSuplGeneradoService {
    @Autowired
    @Qualifier("tiempoSuplGeneradoRepository")
    TiempoSuplGeneradoRepository tiempoSuplGeneradoRepository;


    @Override
    public List<TiempoSuplGenerado> findByPersonal(int idEmpleado, LocalDate fecha) {
        return tiempoSuplGeneradoRepository.findAllByPersonal(idEmpleado,fecha);
    }
}
