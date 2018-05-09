package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.model.AsistenciaJSON;
import com.is.controlincidencias.model.Consulta;
import com.is.controlincidencias.service.AsistenciaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dch/asistencias")
public class RestAsistenciasController {
    private static final Log LOG = LogFactory.getLog(RestAsistenciasController.class);

    /*
     * 0 = Solititud
     * 1 = Bien chido
     * 2 = El numero de tarjeta no se encuentra en el sistenma
     * 3 = Campo tarjeta vacio
     * 4 = Ya hay registro del numero de tarjeta en el sistema en el dia indicado
     * */

    @Autowired
    @Qualifier("asistenciaServiceImpl")
    private AsistenciaService asistenciaService;

    @PostMapping("/consultar")
    public Consulta consultar(@RequestBody Consulta consulta) {
        LOG.info("consultar() -> consulta.noTarjeta=" + consulta.getNoTarjeta());

        boolean valor = asistenciaService.buscarTarjeta(consulta.getNoTarjeta());
        if (!valor) {
            consulta.setEstado(2);
            return consulta;
        }
        valor = asistenciaService.buscarAsistencia(consulta.getFechaRegistro(),
                consulta.getNoTarjeta());
        if (valor) consulta.setEstado(4);
        else consulta.setEstado(1);
        return consulta;
    }

    @PostMapping("/agregar")
    public Consulta agregar(@RequestBody List<AsistenciaJSON> asistencias) {
        LOG.info("agregar() dch.size=" + asistencias.size());
        for (AsistenciaJSON asistenciaJSON : asistencias)
            asistenciaService.agregarAsistencia(asistenciaJSON);
        return new Consulta();
    }
}
