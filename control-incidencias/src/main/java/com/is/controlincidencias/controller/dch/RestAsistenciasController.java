package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.model.AsistenciaJSON;
import com.is.controlincidencias.model.AsistenciaMostrar;
import com.is.controlincidencias.model.ConsultaAsistenciaJSON;
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
    @Autowired
    @Qualifier("asistenciaServiceImpl")
    private AsistenciaService asistenciaService;

    @PostMapping("/consultar/todas")
    public List<AsistenciaJSON> consultarTodas(@RequestBody ConsultaAsistenciaJSON consulta) {
        return asistenciaService.obtenerAsistencias(consulta.getFecha());
    }

    @PostMapping("/obtener/anios")
    public List<AsistenciaMostrar> obtenerAnios(@RequestBody AsistenciaMostrar asistenciaMostrar) {
        log.info("obtenerAnios() tarjeta=" + asistenciaMostrar.toString());
        return asistenciaService.obtenerAniosPorTarjeta(asistenciaMostrar.getTarjeta());
    }

    @PostMapping("/obtener/quincenas")
    public List<String> obtenerQuincenas(@RequestBody AsistenciaMostrar asistencia) {
        log.info("obtenerQuincenas() asistencia" + asistencia.toString());
        return asistenciaService.obtenerQuincenas(asistencia);
    }

    @PostMapping("/obtener/todas")
    public List<AsistenciaJSON> obtenerTodas(@RequestBody AsistenciaMostrar asistenciaMostrar) {
        return asistenciaService.obtenerAsistenciasParaMostrar(asistenciaMostrar);
    }
}
