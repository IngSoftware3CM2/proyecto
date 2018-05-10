package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.model.AsistenciaJSON;
import com.is.controlincidencias.model.ConsultaAsistencia;
import com.is.controlincidencias.service.AsistenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/dch/asistencias")
public class RestAsistenciasController {
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
    public ConsultaAsistencia consultar(@RequestBody ConsultaAsistencia consultaAsistencia) {
        log.info("consultar() -> consultaAsistencia.noTarjeta=" + consultaAsistencia.getNoTarjeta());

        boolean valor = asistenciaService.buscarTarjeta(consultaAsistencia.getNoTarjeta());
        if (!valor) {
            consultaAsistencia.setEstado(2);
            return consultaAsistencia;
        }
        valor = asistenciaService.buscarAsistencia(consultaAsistencia.getFechaRegistro(),
                consultaAsistencia.getNoTarjeta());
        if (valor) consultaAsistencia.setEstado(4);
        else consultaAsistencia.setEstado(1);
        return consultaAsistencia;
    }

    @PostMapping("/agregar")
    public ConsultaAsistencia agregar(@RequestBody List<AsistenciaJSON> asistencias) {
        log.info("agregar() dch.size=" + asistencias.size());
        for (AsistenciaJSON asistenciaJSON : asistencias)
            asistenciaService.agregarAsistencia(asistenciaJSON);
        return new ConsultaAsistencia();
    }
}
