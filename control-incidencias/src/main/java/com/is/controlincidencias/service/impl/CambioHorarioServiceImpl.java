package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.controller.LicenciaPaternidadController;
import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.model.CambioHorarioModel;
import com.is.controlincidencias.repository.CambioHorarioRepository;
import com.is.controlincidencias.service.CambioHorarioService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service("cambioHorarioServiceImpl")
public class CambioHorarioServiceImpl implements CambioHorarioService{

    private static final Log LOG = LogFactory.getLog(CambioHorarioServiceImpl.class);

    @Autowired
    @Qualifier("CambioHorarioRepository")
    private CambioHorarioRepository cambioHorarioRepository;

    @Override
    public void insertaCambioHorario(CambioHorarioModel cambiohorario)
        {
            LOG.info("***************************  Servide IMPL :3");
            LOG.info(cambiohorario);
            Time entrada = Time.valueOf(cambiohorario.getNuevaEntrada() + ":00");
            Time salida =  Time.valueOf(cambiohorario.getNuevaSalida() + ":00");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate fecha = LocalDate.parse(cambiohorario.getFechaIncidencia(), formatter);
            cambioHorarioRepository.guardaJustificanteCH(fecha, entrada, salida, cambiohorario.getJustificación(), cambiohorario.getIdJustificante());
        }

    @Override
    public CambioHorario getSolicitudCambioHorario()
        {
            CambioHorario x = new CambioHorario();
                return x;
        }

    @Override
    public CambioHorario getCambioHorario(int id)
        {
            CambioHorario camb = cambioHorarioRepository.getCambioHorario(id);
            return camb;
        }
}
