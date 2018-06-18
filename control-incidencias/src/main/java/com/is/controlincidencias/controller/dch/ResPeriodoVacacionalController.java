package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.component.ReglasNegocio;
import com.is.controlincidencias.entity.PeriodoInhabil;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.PersonalPeriodoInhabil;
import com.is.controlincidencias.model.ConsultaPersonal;
import com.is.controlincidencias.model.PeriodoVacacionalJSON;
import com.is.controlincidencias.model.PersonalJSON;
import com.is.controlincidencias.service.PeriodoInhabilService;
import com.is.controlincidencias.service.PersonalPeriodoInhabilService;
import com.is.controlincidencias.service.PersonalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dch/asistencias/periodovacacional")
public class ResPeriodoVacacionalController {
    private static final Log LOG = LogFactory.getLog(ResPeriodoVacacionalController.class);
    private int tipoAplica;
    private List<Personal> personal;
    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("periodoInhabilServiceImpl")
    private PeriodoInhabilService periodoInhabilService;

    @Autowired
    @Qualifier("personalPeriodoInhabilServiceImpl")
    private PersonalPeriodoInhabilService personalPeriodoInhabilService;


    @Autowired
    @Qualifier("reglasNegocioComponent")
    private ReglasNegocio reglasNegocio;

    @PostMapping("/personal")
    public List<ConsultaPersonal> consultaPer(@RequestBody PersonalJSON personalJSON) {
        String tipo = "";
        tipoAplica=personalJSON.getValorTipo();
        if(personalJSON.getValorTipo() == 1){
            tipo= "ROLE_DOC";
        }else if(personalJSON.getValorTipo() == 2){
            tipo="ROLE_PAAE";
        }else{
            tipo="AMBOS";
        }
        personal = personalService.getPersonalByTipo(tipo);
        List<ConsultaPersonal> listPersonal = new ArrayList<>();
        for (Personal pe : personal) {
            ConsultaPersonal consultaPersonal = new ConsultaPersonal();
            consultaPersonal.setIdPersonal(pe.getIdEmpleado());
            consultaPersonal.setNombre(pe.getNombre());
            consultaPersonal.setNoTarjeta(pe.getNoTarjeta());
            listPersonal.add(consultaPersonal);
        }
        return listPersonal;
    }

    @PostMapping("/agregar")
    public int registrarDatos(@RequestBody PeriodoVacacionalJSON periodoVacacionalJSON) {
        LOG.info("------------------------------------------");
        LOG.info(periodoVacacionalJSON.getFechaFin());
        LOG.info(periodoVacacionalJSON.getFechaInicio());
        LOG.info(periodoVacacionalJSON.getListId());

        LocalDate fechaInicio = periodoVacacionalJSON.getFechaInicio();
        LocalDate fechaFin = periodoVacacionalJSON.getFechaFin();
        List<Personal> personalSin = new ArrayList<>();
        if(fechaInicio ==null || fechaFin == null){
            return -2;
        }
        if(periodoVacacionalJSON.getListId().isEmpty()){
            return -2;
        }

        if(reglasNegocio.rn28(fechaInicio,fechaFin)){
         return -1;
        }
        for (Personal p:personal) {
            boolean estuvo = false;
            for(int id: periodoVacacionalJSON.getListId()){
                if(p.getIdEmpleado()==id){
                    estuvo=true;
                    break;
                }
            }
            if(!estuvo){
                personalSin.add(p);
            }
        }
        PeriodoInhabil pih = new PeriodoInhabil();
        pih.setInicio(fechaInicio);
        pih.setFin(fechaFin);
        pih.setDescripcion("Vacaciones");
        if(tipoAplica==1){
            pih.setPermisodocente(true);
            pih.setPermisopaee(false);
        }else if(tipoAplica==2){
            pih.setPermisodocente(false);
            pih.setPermisopaee(true);
        }else{
            pih.setPermisodocente(true);
            pih.setPermisopaee(true);
        }
        Integer idPeriodo = periodoInhabilService.findMaxIdPeriodo();
        if(idPeriodo==null){
            idPeriodo=0;
        }
        pih.setIdperiodo(idPeriodo + 1);
        PeriodoInhabil ultimo = periodoInhabilService.savePeriodoInhabil(pih);
        if(!personalSin.isEmpty()){
            Integer max = personalPeriodoInhabilService.getMaxId();
            if(max==null){
                max=1;
            }else{
                max=max+1;
            }
            for (Personal psin:personalSin) {
                PersonalPeriodoInhabil ppin= new PersonalPeriodoInhabil();
                ppin.setPersonal(psin);
                ppin.setPeriodoInhabil(ultimo);
                ppin.setIdpersonalperiodoinhabil(max);
                max=max+1;
                personalPeriodoInhabilService.savePersonalSinPeriodo(ppin);
            }
        }
        return 1;
    }
}
