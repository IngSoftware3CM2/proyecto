package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.entity.UnidadMedica;
import com.is.controlincidencias.entity.Zona;
import com.is.controlincidencias.model.ConsultaUnidadMedica;
import com.is.controlincidencias.model.ZonaJSON;
import com.is.controlincidencias.service.UnidadMedicaService;
import com.is.controlincidencias.service.ZonaService;
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
@RequestMapping("/personal/justificantes/tipoa")
public class ResUnidadMedica {

    @Autowired
    @Qualifier("zonaServiceImpl")
    private ZonaService zonaService;

    @Autowired
    @Qualifier("unidadMedicaServiceImpl")
    private UnidadMedicaService unidadMedicaService;

    @PostMapping("/unidadMedica")
    public List<ConsultaUnidadMedica> consultaUnidadMedica(@RequestBody ZonaJSON zonaJSON) {
        Zona zona = zonaService.getZonabyNombre(zonaJSON.getNombre());
        List<UnidadMedica> unidadMedica;
        List<ConsultaUnidadMedica> unidades = new ArrayList<>();
        //log.info("Zona: " + zona.getNombre());
        unidadMedica = unidadMedicaService.getunidadesMedicasByZona(zona);
        for (UnidadMedica unidad : unidadMedica) {
            ConsultaUnidadMedica consultaUnidadMedica = new ConsultaUnidadMedica();
            consultaUnidadMedica.setIdUnidadMedica(unidad.getIdUnidad());
            consultaUnidadMedica.setNameUnidadMedica(unidad.getNombre());
            unidades.add(consultaUnidadMedica);
        }
        log.info(unidades.toString());
        return unidades;
    }
}
