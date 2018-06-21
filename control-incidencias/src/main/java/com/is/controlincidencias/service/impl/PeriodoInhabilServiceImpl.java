package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.PeriodoInhabil;
import com.is.controlincidencias.repository.PeriodoInhabilRepository;
import com.is.controlincidencias.service.PeriodoInhabilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("periodoInhabilServiceImpl")
public class PeriodoInhabilServiceImpl implements PeriodoInhabilService {

    @Autowired
    @Qualifier("periodoInhabilRepository")
    PeriodoInhabilRepository periodoInhabilRepository;

    @Override
    public PeriodoInhabil savePeriodoInhabil(PeriodoInhabil periodoInhabil) {
        return periodoInhabilRepository.save(periodoInhabil);
    }

    @Override
    public Integer findMaxIdPeriodo() {
        return periodoInhabilRepository.selectMaxIdPeriodoInhabil();
    }

    @Override
    public void updatePeriodoInhabil(String nombreArchivo, int idPeriodo) {
        periodoInhabilRepository.updatePeriodoInhabil(idPeriodo+"_"+nombreArchivo,idPeriodo);
    }
}
