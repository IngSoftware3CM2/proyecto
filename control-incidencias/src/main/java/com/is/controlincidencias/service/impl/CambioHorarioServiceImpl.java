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
            Time entrada = Time.valueOf(cambiohorario.getNuevaEntrada());
            Time salida =  Time.valueOf(cambiohorario.getNuevaSalida());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate fecha = LocalDate.parse(cambiohorario.getFechaIncidencia(), formatter);
            Date fecha2 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //enn el formato que el jsutificante lo quiere -3- antes el LocalDate.now() era "fecha"
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
//            Time entrada = Time.valueOf(chm.getNuevaEntrada() + ":00");
  //          Time salida =  Time.valueOf(chm.getNuevaSalida() + ":00");
            LOG.info("*** SERVIVE IMPL Justificacion" + chm.getJustificacion());
            cambioHorarioRepository.updateCambioHorario(chm.getJustificacion(), chm.getIdJustificante());
        }
    @Override
    public int getIdEmpleadoByIdIncidencia(int id)
        {
            return cambioHorarioRepository.getIdEmpleadoByIdIncidencia(id);
        }

    @Override
    public int getIdJustificanteByIdEmpleado(int id)
        {
            return cambioHorarioRepository.getIdJustificanteByIdEmpleado(id);
        }
    @Override
    public boolean existsByIdjustificante(int id) {
        return cambioHorarioRepository.existsByJustificante_IdJustificante(id);
    }

    @Override
    public String getHoraEntrada(int id, String fecha)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate fecha2 = LocalDate.parse(fecha, formatter);
            return cambioHorarioRepository.getHoraEntrada(id, fecha2);
        }

    @Override
    public String getHoraSalida(int id, String fecha)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate fecha2 = LocalDate.parse(fecha, formatter);
        return cambioHorarioRepository.getHoraSalida(id, fecha2);
    }

    @Override
    public String getHoraS(int id, String dia)
        {
            return cambioHorarioRepository.getHoraS(id, dia);
        }

    @Override
    public String getHoraE(int id, String dia)
        {
            return cambioHorarioRepository.getHoraE(id, dia);
        }
}

