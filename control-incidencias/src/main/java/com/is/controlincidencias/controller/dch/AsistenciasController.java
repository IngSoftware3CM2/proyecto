package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.service.AsistenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/dch/asistencias")
public class AsistenciasController {

    private static final String REGISTRAR_ASISTENCIAS = "dch/asistencias-registrar";
    private static final String MODIFICAR_ASISTENCIA = "dch/asistencias-modificar";
    private static final String MOSTRAR_ASISTENCIAS = "dch/asistencias-mostrar";
    private static final String ELIMINAR_ASISTENCIA = "dch/asistencias-eliminar";

    private static final String RESULTADO = "resultado";
    private static final String MODELO = "modelo";
    private static final int OPERACION_EXITOSA = 3;
    private static final int ERROR_AL_MODIFICAR = 4;
    private static final int ESTADO_INICIAL = 0;
    private static final String VALIDAR = "validar";


    private final AsistenciaService asistenciaService;

    @Autowired
    public AsistenciasController(
            @Qualifier("asistenciaServiceImpl") AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping({"", "/"})
    public String redirectInicio() {
        return "redirect:/dch";
    }

    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute(MODELO, new AsistenciaForm());
        return REGISTRAR_ASISTENCIAS;
    }

    @PostMapping(params = "buscar", value = "/registro")
    public String buscar(@Valid @ModelAttribute(name = "modelo") AsistenciaForm modelo,
            BindingResult bindingResult, Model model) {
        log.info("registarBuscar()");


        AsistenciaForm asistencia = asistenciaService.buscarAsistencia(modelo);

        model.addAttribute(MODELO, asistencia);

        log.info("saliendo de registarBuscar()");
        return REGISTRAR_ASISTENCIAS;
    }

    @PostMapping(params = "modificar", value = "/modificar")
    public String modificarPOST(@Valid @ModelAttribute(name = "modelo") AsistenciaForm modelo,
            BindingResult bindingResult, Model model) {
        int resultado;
        log.info("modificarPOST() " + modelo.toString());

        if (!bindingResult.hasErrors()) {
            Asistencia a = asistenciaService.modificarAsistencia(modelo);
            if (a != null) {
                resultado = OPERACION_EXITOSA;
                model.addAttribute(MODELO, new AsistenciaForm());
            } else resultado = ERROR_AL_MODIFICAR;
        } else {
            log.info("ERROR en modificarPOST()");
            resultado = ERROR_AL_MODIFICAR;
        }

        model.addAttribute(RESULTADO, resultado);
        model.addAttribute(VALIDAR, true);
        log.info("saliendo de modificarPOST()");
        return MODIFICAR_ASISTENCIA;
    }

    @PostMapping(params = "consultar", value = "/modificar")
    public String consultarPOST(@Valid @ModelAttribute(name = "modelo") AsistenciaForm modelo,
            BindingResult bindingResult,
            Model model) {
        log.info("consultarPOST()");
        int resultado;

        if (bindingResult.hasErrors())
            log.error("consultarPOST() errores");

        AsistenciaForm asistencia = new AsistenciaForm();
        resultado = asistenciaService.existeAsistencia(modelo);
        if (resultado == ESTADO_INICIAL)
            asistencia = asistenciaService.buscarAsistencia(modelo);

        model.addAttribute(RESULTADO, resultado);
        model.addAttribute(VALIDAR, false);
        model.addAttribute(MODELO, asistencia);

        log.info("saliendo de consultarPOST()");
        return MODIFICAR_ASISTENCIA;
    }

    @GetMapping("/modificar")
    public String modificarGET(Model model) {
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

    @GetMapping("/eliminar")
    public String eliminar() {
        return ELIMINAR_ASISTENCIA;
    }
}
