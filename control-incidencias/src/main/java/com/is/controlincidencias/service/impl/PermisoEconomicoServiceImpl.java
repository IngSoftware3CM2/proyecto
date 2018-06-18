package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.controller.LicenciaPaternidadController;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.PermisoEconomicoRepository;
import com.is.controlincidencias.repository.PersonalQuincenaRepository;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.PermisoEconomicoService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("permisoEconomicoServiceImpl")
public class PermisoEconomicoServiceImpl implements PermisoEconomicoService {

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);


    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("personalQuincenaRepository")
    private PersonalQuincenaRepository personalQuincenaRepository;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Autowired
    @Qualifier("permisoEconomicoRepository")
    private PermisoEconomicoRepository permisoEconomicoRepository;

    @Autowired
    public PermisoEconomicoServiceImpl(@Qualifier("permisoEconomicoRepository") PermisoEconomicoRepository permisoEconomicoRepository, @Qualifier("justificanteRepository") JustificanteRepository justificanteRepository) {
        this.permisoEconomicoRepository = permisoEconomicoRepository;
        this.justificanteRepository = justificanteRepository;
    }


    @Override
    public int preguntarAnoQuincena(int idempleado, int idquincena, String tipo) {
        Integer diasporquincena = personalQuincenaRepository.preguntarPorDiasQuincena(idempleado,idquincena);
        LOG.info("--------------"+ diasporquincena);
        Integer diasporano = personalQuincenaRepository.preguntarPorDiasAno(idempleado);
        LOG.info("--------------"+ diasporano);
        if (diasporano!=null){
            if ((tipo.equals("ROLE_DOC") && diasporano < 10) || (tipo.equals("ROLE_PAEE") && diasporano < 12)) {
                if (diasporquincena != null) {
                    if (diasporquincena < 3) {
                        personalQuincenaRepository.updateQuincena(diasporquincena+1, idempleado,idquincena);
                        return 1;
                    } else {
                         return 0;
                         //no puedes hacer otro permiso esta quincena
                    }
                } else {
                    Integer idpersonalquincena = personalQuincenaRepository.selectMaxIdPersonalQuincena();
                    personalQuincenaRepository.insertRegistro(idpersonalquincena+1,1, idempleado,idquincena);
                    return 1;
                    //nuevo registro, primer permiso economico de la quincena
                }
            }
            else{
                return -1;
                //ya no puedes, tienes todas los permisos posibles
            }
        }
        else{
            Integer idpersonalquincena = personalQuincenaRepository.selectMaxIdPersonalQuincena();
            if (idpersonalquincena!=null){
                personalQuincenaRepository.insertRegistro(idpersonalquincena,1, idempleado,idquincena);
            }
            else{
                personalQuincenaRepository.insertRegistro(1,1, idempleado,idquincena);
            }
            //nuevo registro, primer permiso economico de toodo el año
            return 1;
        }
    }



    @Override
    public boolean existsByIdjustificante(int id) {
        return permisoEconomicoRepository.existsByJustificante_IdJustificante(id);
    }

    @Override
    public void registrarJustificante(int idEmpleado, Incidencia incidencia) {
        Date fecha = new Date();
        justificanteRepository.altaJustificante("Espera",fecha,4,idEmpleado);
        Integer idPermisoEconomico = permisoEconomicoRepository.selectMaxIdPermisoEconomico();
        if(idPermisoEconomico!=null){
            permisoEconomicoRepository.insertRegistro(idPermisoEconomico+1,justificanteRepository.selectMaxIdPermisoEconomico());
            incidenciaService.updateIdJustificante(justificanteRepository.selectMaxIdPermisoEconomico(),incidencia.getIdIncidencia());
        }
        else{
            permisoEconomicoRepository.insertRegistro(1,justificanteRepository.selectMaxIdPermisoEconomico());
            incidenciaService.updateIdJustificante(justificanteRepository.selectMaxIdPermisoEconomico(),incidencia.getIdIncidencia());
        }

    }
}
