package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.models.Consulta;
import com.is.controlincidencias.service.AsistenciaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/asistencias")
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
        LOG.info("consultar() -> consulta=" + consulta.toString());

        boolean valor = asistenciaService.buscarTarjeta(consulta.getNoTarjeta());
        if (!valor) {
            consulta.setEstado(2);
            return consulta;
        }
        valor = asistenciaService.buscarAsistencia(consulta.getFechaRegistro(), consulta.getNoTarjeta());
        if (valor)
            consulta.setEstado(4);
        else
            consulta.setEstado(1);
        return consulta;
    }

}
