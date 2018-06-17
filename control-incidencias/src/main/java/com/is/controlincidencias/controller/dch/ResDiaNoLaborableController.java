package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.component.ReglasNegocio;
import com.is.controlincidencias.entity.PeriodoInhabil;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.PersonalPeriodoInhabil;
import com.is.controlincidencias.model.ConsultaPersonal;
import com.is.controlincidencias.model.DiaNoLaborableJSON;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dch/asistencias/dianolaborable")
public class ResDiaNoLaborableController {

    private static final Log LOG = LogFactory.getLog(ResDiaNoLaborableController.class);
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
            tipo="ROLE_PAEE";
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
    public int registrarDatos(@RequestBody DiaNoLaborableJSON diaNoLaborableJSON) {
        LOG.info("------------------------------------------");
        LOG.info(diaNoLaborableJSON.getFechaNH());
        LOG.info(diaNoLaborableJSON.getJustificacionNH());
        LOG.info(diaNoLaborableJSON.getListId());
        LocalDate fecha = diaNoLaborableJSON.getFechaNH();
        List<Personal> personalSin = new ArrayList<>();
        if(fecha ==null){
            return -2;
        }
        DayOfWeek dow = fecha.getDayOfWeek();
        if(diaNoLaborableJSON.getListId().isEmpty()){
            return -2;
        }
        if(diaNoLaborableJSON.getJustificacionNH().equals("")){
            return -2;
        }
        if(reglasNegocio.rn54(dow)){
            LOG.info(dow);
            return -1; //Fecha invalida
        }
        for (Personal p:personal) {
            boolean estuvo = false;
            for(int id: diaNoLaborableJSON.getListId()){
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
        pih.setInicio(fecha);
        pih.setFin(fecha);
        pih.setDescripcion(diaNoLaborableJSON.getJustificacionNH());
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
/*
    @PostMapping("/upload")
    public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        //0. notice, we have used MultipartHttpServletRequest
        //1. get the files from the request object
        Iterator<String> itr =  request.getFileNames();
        MultipartFile mpf = request.getFile(itr.next());
        LOG.info("---------UPLOADED------------");
        String nameArchivo = mpf.getOriginalFilename();
        return "";
    }*/
}
