package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.model.CambioHorarioModel;
import com.is.controlincidencias.repository.CambioHorarioRepository;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.service.CambioHorarioService;
import com.is.controlincidencias.service.IncidenciaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service("cambioHorarioServiceImpl")
public class CambioHorarioServiceImpl implements CambioHorarioService{

    private static final Log LOG = LogFactory.getLog(CambioHorarioServiceImpl.class);

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("CambioHorarioRepository")
    private CambioHorarioRepository cambioHorarioRepository;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Override
    public void insertaCambioHorario(CambioHorarioModel cambiohorario, int idincidencia)
        {
            LOG.info("***************************  Servide IMPL :3");
            LOG.info(cambiohorario);
            Time entrada = Time.valueOf(cambiohorario.getNuevaEntrada() + ":00");
            Time salida =  Time.valueOf(cambiohorario.getNuevaSalida() + ":00");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate fecha = LocalDate.parse(cambiohorario.getFechaIncidencia(), formatter);
            Date fecha2 = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()); //enn el formato que el jsutificante lo quiere -3-
            justificanteRepository.altaJustificante("Espera",fecha2,0,cambiohorario.getIdJustificante()); //idJustificante es el noempleado :3
            List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
            LOG.info("--- EL ID ES " + ids.get(ids.size() - 1));
            cambioHorarioRepository.guardaJustificanteCH(fecha, entrada, salida, cambiohorario.getJustificacion(), ids.get(ids.size() - 1));
            incidenciaService.updateIdJustificante(ids.get(ids.size() - 1), idincidencia);
        }

    @Override
    public CambioHorario  getIdCambioHorario(int idJustificante)
        {
            LOG.info("ID JUSTIFICANTE ES " + idJustificante);
            return cambioHorarioRepository.getIdCambioHorario(idJustificante);
        }

    @Override
    public void updateCambioHorario(CambioHorarioModel chm)
        {
            Time entrada = Time.valueOf(chm.getNuevaEntrada() + ":00");
            Time salida =  Time.valueOf(chm.getNuevaSalida() + ":00");
            LOG.info("*** SERVIVE IMPL Justificacion" + chm.getJustificacion());
            cambioHorarioRepository.updateCambioHorario(entrada, salida, chm.getJustificacion(), chm.getIdJustificante());
        }
    @Override
    public int getNoEmpleadoByIdIncidencia(int id)
        {
            return cambioHorarioRepository.getNoEmpleadoByIdIncidencia(id);
        }

    @Override
    public int getIdJustificanteByNoEmpleado(int id)
        {
            return cambioHorarioRepository.getIdJustificanteByNoEmpleado(id);
        }
    @Override
    public boolean existsByIdjustificante(int id) {
        return cambioHorarioRepository.existsByJustificante_IdJustificante(id);
    }

}

