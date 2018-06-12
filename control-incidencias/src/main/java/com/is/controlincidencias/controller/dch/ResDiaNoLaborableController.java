package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.ConsultaPersonal;
import com.is.controlincidencias.model.PersonalJSON;
import com.is.controlincidencias.service.PersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dch/asistencias/dianolaborable")
public class ResDiaNoLaborableController {

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @PostMapping("/personal")
    public List<ConsultaPersonal> consultaUnidadMedica(@RequestBody PersonalJSON personalJSON) {
        String tipo = "";
        List<Personal> personal;
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
}
