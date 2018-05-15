package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.converter.StringToLocalDate;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.model.PermisoEconomicoModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.PermisoEconomicoRepository;
import com.is.controlincidencias.service.PermisoEconomicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service("permisoEconomicoServiceImpl")
public class PermisoEconomicoServiceImpl implements PermisoEconomicoService {

    private final PermisoEconomicoRepository permisoEconomicoRepository;

    private final JustificanteRepository justificanteRepository;

    @Autowired
    public PermisoEconomicoServiceImpl(@Qualifier("permisoEconomicoRepository") PermisoEconomicoRepository permisoEconomicoRepository, @Qualifier("justificanteRepository") JustificanteRepository justificanteRepository) {
        this.permisoEconomicoRepository = permisoEconomicoRepository;
        this.justificanteRepository = justificanteRepository;
    }


    @Override
    public int addPermisoEconomico(PermisoEconomicoModel permisoEconomicoModel, Justificante justificante) {
        Date fecha = new Date();
        int idEmpleado=justificante.getPersonal().getIdEmpleado();
        justificanteRepository.altaJustificante("Espera",fecha,4,idEmpleado);
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        LocalDate fechaIncidencia = StringToLocalDate.tryParseDate(permisoEconomicoModel.getFechaIncidencia());
        permisoEconomicoRepository.addPermisoEconomico(fechaIncidencia, ids.get(ids.size()-1));
        return ids.get(ids.size()-1);
    }

    @Override
    public PermisoEconomico findByJustificante(Justificante justificante) {
        return permisoEconomicoRepository.findByJustificante(justificante);
    }

    @Override
    public boolean existsByIdjustificante(int id) {
        return permisoEconomicoRepository.existsByJustificante_IdJustificante(id);
    }
}
