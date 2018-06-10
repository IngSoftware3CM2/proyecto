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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

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
        AsistenciaForm a = new AsistenciaForm();
        a.setFecha(LocalDate.now());
        model.addAttribute(MODELO, a);
        return REGISTRAR_ASISTENCIAS;
    }

    @PostMapping(params = "registrar", value = "/registrar")
    public String registrar(@Valid @ModelAttribute(name = "modelo") AsistenciaForm modelo,
            BindingResult bindingResult, Model model) {
        int resultado;
        log.info("registrar() " + modelo.toString());
        AsistenciaForm form = new AsistenciaForm();
        form.setFecha(modelo.getFecha());
        if (!bindingResult.hasErrors()) {
            if (asistenciaService.modificarAsistencia(modelo) != null) {
                resultado = OPERACION_EXITOSA;
                form.setFecha(LocalDate.now());
            } else resultado = ERROR_AL_MODIFICAR;
        } else {
            log.info("ERROR en registrar()");
            resultado = ERROR_AL_MODIFICAR;
        }

        model.addAttribute(MODELO, form);
        model.addAttribute(RESULTADO, resultado);
        model.addAttribute(VALIDAR, true);
        log.info("saliendo de modificarPOST()");
        return REGISTRAR_ASISTENCIAS;
    }

    @PostMapping(params = "buscar", value = "/registrar")
    public String buscar(@Valid @ModelAttribute(name = "modelo") AsistenciaForm modelo,
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
        return REGISTRAR_ASISTENCIAS;
    }

    @GetMapping("/eliminar/{idAsistencia}")
    public String eliminar(@PathVariable("idAsistencia") Integer id, Model model) {
        asistenciaService.eliminarAsistenciaPorId(id);
        AsistenciaForm a = new AsistenciaForm();
        a.setFecha(LocalDate.now());
        model.addAttribute(MODELO, a);
        return REGISTRAR_ASISTENCIAS;
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
