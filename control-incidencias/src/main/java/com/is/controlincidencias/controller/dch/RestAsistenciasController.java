package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.model.AsistenciaJSON;
import com.is.controlincidencias.model.ConsultaAsistenciaJSON;
import com.is.controlincidencias.service.AsistenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/consultar/todas")
    public List<AsistenciaJSON> consultarTodas(@RequestBody ConsultaAsistenciaJSON consulta) {
        log.info(consulta.toString());
        return asistenciaService.obtenerAsistencias(consulta.getFecha());
    }
}
