package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.model.JustificateTiempoSuplModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.TiempoSuplRepository;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.TiempoSuplementarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service("tiempoSuplementarioServiceImpl")
public class TiempoSuplementarioServiceImpl implements TiempoSuplementarioService {

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Autowired
    @Qualifier("tiempoSuplRepository")
    private TiempoSuplRepository tiempoSuplRepository;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Override
    public int saveJustificanteTS(JustificateTiempoSuplModel justificateTiempoSuplModel, Incidencia incidencia) {
        Date fecha = new Date();
        int horas=0;
        //Aqui cambia dependiendo el No empleado.
        int idEmpleado=incidencia.getPersonal().getIdEmpleado();
        justificanteRepository.altaJustificante("Espera",fecha,5,idEmpleado);
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        LocalDate fechaIncidencia = incidencia.getFechaRegistro();
        Integer tiempoCubrir = justificateTiempoSuplModel.getTiempocubrir();
        int idJustificante =ids.get(ids.size()-1);
        tiempoSuplRepository.saveJustificanteTS(fechaIncidencia,tiempoCubrir,idJustificante);
        if(incidencia.getHorasFaltantes() == tiempoCubrir){
            horas = 0;
        }else{
            horas = incidencia.getHorasFaltantes() - tiempoCubrir;
        }
        incidenciaService.updateIdIncidenciaAndHorasCubrir(ids.get(ids.size()-1),incidencia.getIdIncidencia(),horas);
        return idJustificante;
    }
    @Override
    public boolean existsByIdjustificante(int id) {
        return tiempoSuplRepository.existsByJustificante_IdJustificante(id);
    }

}
