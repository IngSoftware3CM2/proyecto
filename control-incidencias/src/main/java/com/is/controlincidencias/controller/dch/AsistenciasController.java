package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.service.AsistenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/dch/asistencias")
public class AsistenciasController {

    private static final String REGISTRAR_ASISTENCIAS = "dch/asistencias-registrar";
    private static final String MODIFICAR_ASISTENCIA = "dch/asistencias-modificar";
    private static final String MOSTRAR_ASISTENCIAS = "dch/asistencias-mostrar";

    private static final String BUSCAR_DESHABILITADO = "buscar_deshabilitado";
    private static final String FECHA_DESHABILITADO = "fecha_deshabilitado";
    private static final String TARJETA_DESHABILITADO = "tarjeta_deshabilitado";
    private static final String REGISTRAR_DESHABILITADO = "registrar_deshabilitado";

    private static final String RESULTADO = "resultado";
    private static final String MODELO = "modelo";
    private static final int NO_EXISTE_TARJETA = 1;
    private static final int REGISTRO_DUPLICADO = 2;
    private static final int OPERACION_EXITOSA = 3;
    private static final int ERROR_HORA = 4;
    private static final int FECHA_ASISTENCIA_INVALIDA = 5;

    private static final int ESTADO_INICIAL = 0;
    private static final String VALIDAR = "validar";

    @Autowired
    @Qualifier("asistenciaServiceImpl")
    private AsistenciaService asistenciaService;

    @GetMapping({"", "/"})
    public String redirectInicio() {
        return "redirect:/dch";
    }

    @GetMapping("/registrar")
    public String registrar(Model model) {
        AsistenciaForm a = new AsistenciaForm();
        a.setFecha(LocalDate.now());
        model.addAttribute(MODELO, a);
        model.addAttribute(BUSCAR_DESHABILITADO, false);
        model.addAttribute(FECHA_DESHABILITADO, false);
        model.addAttribute(TARJETA_DESHABILITADO, false);
        model.addAttribute(REGISTRAR_DESHABILITADO, true);
        return REGISTRAR_ASISTENCIAS;
    }

    @PostMapping(params = "registrar", value = "/registrar")
    public String registrar(@Valid @ModelAttribute(name = "modelo") AsistenciaForm modelo,
            BindingResult bindingResult, Model model) {
        int resultado = ERROR_HORA;
        log.info("registrar() " + modelo.toString());

        AsistenciaForm a = new AsistenciaForm();
        a.setFecha(LocalDate.now());
        if (bindingResult.hasErrors())
            log.error("registrar() errores");

        if (modelo.getId() == null) {
            if (asistenciaService.agregarAsistencia(modelo) != null)
                resultado = OPERACION_EXITOSA;
        } else {
            if (asistenciaService.modificarAsistencia(modelo) !=null)
                resultado = OPERACION_EXITOSA;
        }

        if (resultado == ERROR_HORA) {
            a.setTarjeta(modelo.getTarjeta());
            a.setFecha(modelo.getFecha());
            a.setNombre(modelo.getNombre());
            a.setId(modelo.getId());
            model.addAttribute(BUSCAR_DESHABILITADO, true);
            model.addAttribute(FECHA_DESHABILITADO, true);
            model.addAttribute(TARJETA_DESHABILITADO, true);
            model.addAttribute(REGISTRAR_DESHABILITADO, false);
        } else {
            model.addAttribute(BUSCAR_DESHABILITADO, false);
            model.addAttribute(FECHA_DESHABILITADO, false);
            model.addAttribute(TARJETA_DESHABILITADO, false);
            model.addAttribute(REGISTRAR_DESHABILITADO, true);
        }

        model.addAttribute(MODELO, a);
        model.addAttribute(RESULTADO, resultado);
        log.info("saliendo de registrar()");
        return REGISTRAR_ASISTENCIAS;
    }

    @PostMapping(params = "buscar", value = "/registrar")
    public String buscar(@Valid @ModelAttribute(name = "modelo") AsistenciaForm modelo,
            BindingResult bindingResult, Model model) {
        log.info("buscar()");
        int resultado;

        if (bindingResult.hasErrors())
            log.error("buscar() errores");

        AsistenciaForm asistencia = new AsistenciaForm();
        resultado = asistenciaService.existeAsistencia(modelo);
        if (resultado == FECHA_ASISTENCIA_INVALIDA) { // trayectoria D
            asistencia.setFecha(LocalDate.now());
            asistencia.setTarjeta(modelo.getTarjeta());
            model.addAttribute(BUSCAR_DESHABILITADO, false);
            model.addAttribute(FECHA_DESHABILITADO, false);
            model.addAttribute(TARJETA_DESHABILITADO, false);
            model.addAttribute(REGISTRAR_DESHABILITADO, true);
            // Trayectoria F y E
        }else if (resultado == NO_EXISTE_TARJETA || resultado == REGISTRO_DUPLICADO){
            asistencia.setFecha(modelo.getFecha());
            model.addAttribute(BUSCAR_DESHABILITADO, false);
            model.addAttribute(FECHA_DESHABILITADO, false);
            model.addAttribute(TARJETA_DESHABILITADO, false);
            model.addAttribute(REGISTRAR_DESHABILITADO, true);
        } else {
            asistencia = asistenciaService.buscarAsistencia(modelo);
            model.addAttribute(BUSCAR_DESHABILITADO, true);
            model.addAttribute(FECHA_DESHABILITADO, true);
            model.addAttribute(TARJETA_DESHABILITADO, true);
            model.addAttribute(REGISTRAR_DESHABILITADO, false);
            model.addAttribute(VALIDAR, true);
        }
        model.addAttribute(RESULTADO, resultado);
        model.addAttribute(MODELO, asistencia);


        log.info("saliendo de buscar()");
        return REGISTRAR_ASISTENCIAS;
    }

    @GetMapping("/eliminar/{idAsistencia}")
    public String eliminar(@PathVariable("idAsistencia") Integer id, Model model) {
        asistenciaService.eliminarAsistenciaPorId(id);
        AsistenciaForm a = new AsistenciaForm();
        a.setFecha(LocalDate.now());
        model.addAttribute(MODELO, a);
        return "redirect:/dch/asistencias/registrar";
    }


    @GetMapping("/modificar")
    public String mostrarModificar(Model model) {
        AsistenciaForm asistencia = new AsistenciaForm();
        model.addAttribute(MODELO, asistencia);
        model.addAttribute(RESULTADO, ESTADO_INICIAL);
        model.addAttribute(VALIDAR, false);

        log.info("saliendo de modificarGET()");
        return MODIFICAR_ASISTENCIA;
    }

    @GetMapping("/mostrar")
    public String mostrar() {
        return MOSTRAR_ASISTENCIAS;
    }


}
