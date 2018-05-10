package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.entity.UnidadMedica;
import com.is.controlincidencias.entity.Zona;
import com.is.controlincidencias.model.ConsultaUnidadMedica;
import com.is.controlincidencias.model.ZonaJSON;
import com.is.controlincidencias.service.UnidadMedicaService;
import com.is.controlincidencias.service.ZonaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personal/justificantes/tipoa")
public class ResUnidadMedica {
    private static final Log LOG = LogFactory.getLog(ResUnidadMedica.class);

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
        LOG.info("Zona: " + zona.getNombre());
        unidadMedica = unidadMedicaService.getunidadesMedicasByZona(zona);
        for (UnidadMedica unidad : unidadMedica) {
            ConsultaUnidadMedica consultaUnidadMedica = new ConsultaUnidadMedica();
            consultaUnidadMedica.setIdUnidadMedica(unidad.getIdUnidad());
            consultaUnidadMedica.setNameUnidadMedica(unidad.getNombre());
            unidades.add(consultaUnidadMedica);
        }
        LOG.info(unidades);
        return unidades;
    }
}
