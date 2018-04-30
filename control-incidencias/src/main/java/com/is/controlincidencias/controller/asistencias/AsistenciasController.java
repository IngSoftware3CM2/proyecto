package com.is.controlincidencias.controller.asistencias;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.service.AsistenciaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asistencias")
public class AsistenciasController {
    private static final Log LOG = LogFactory.getLog(AsistenciasController.class);
    private static final String INICIO_ASISTENCIAS = "inicio-asistencias";
    private static final String REGISTRAR_ASISTENCIA = "registrar-asistencias";
    private static final String MODIFICAR_ASISTENCIA = "modificar-asistencias";
    private static final String MOSTRAR_ASISTENCIA = "mostrar-asistencias";
    private static final String ELIMINAR_ASISTENCIA = "eliminar-asistencias";
    private static final String RESULTADO = "resultado";
    private static final String MODELO = "modelo";
    private static final int MODIFICACION_EXITOSA = 3;
    private static final int ERROR_AL_MODIFICAR = 4;
    private static final int ESTADO_INICIAL = 0;


    @Autowired
    @Qualifier("asistenciaServiceImpl")
    private AsistenciaService asistenciaService;


    @GetMapping({"", "/"})
    public String inicio() {
        return INICIO_ASISTENCIAS;
    }

    @GetMapping("/registrar")
    public String registrar() {
        return REGISTRAR_ASISTENCIA;
    }

    @PostMapping(params = "modificar", value = "/modificar")
    public String modificarPOST(@ModelAttribute(name = "modelo") AsistenciaForm modelo, Model model) {
        LOG.info("modificarPOST()");
        int resultado;
        AsistenciaForm asistencia = new AsistenciaForm();
        if (validarFormato(modelo)) {
            Asistencia a = asistenciaService.modificarAsistencia(modelo);
            if (a != null)
                resultado = MODIFICACION_EXITOSA;
            else
                resultado = ERROR_AL_MODIFICAR;
        } else
            resultado = ERROR_AL_MODIFICAR;


        model.addAttribute(RESULTADO, resultado);
        model.addAttribute(MODELO, asistencia);
        LOG.info("saliendo de modificarPOST()");
        return MODIFICAR_ASISTENCIA;
    }

    private boolean validarFormato(AsistenciaForm modelo) {
        if (!modelo.getFecha().matches("^\\d{4}-\\d{2}-\\d{2}$"))
            return false;
        if (!modelo.getHoraEntrada().matches("^\\d{2}:\\d{2}$"))
            return false;
        return modelo.getHoraSalida().matches("^\\d{2}:\\d{2}$");
    }

    @PostMapping(params = "consultar", value = "/modificar")
    public String consultarPOST(@ModelAttribute(name = "modelo") AsistenciaForm modelo, Model model) {
        LOG.info("consultarPOST: ");
        int resultado;

        AsistenciaForm asistencia = new AsistenciaForm();
        resultado = asistenciaService.existeAsistencia(modelo);
        if (resultado == ESTADO_INICIAL) {
            asistencia = asistenciaService.buscarAsistencia(modelo);
        }
        model.addAttribute(RESULTADO, resultado);
        model.addAttribute(MODELO, asistencia);

        LOG.info("saliendo de consultarPOST()");
        return MODIFICAR_ASISTENCIA;
    }

    @GetMapping("/modificar")
    public String modificarGET(Model model) {
        LOG.info("modificarGET()");

        AsistenciaForm asistencia = new AsistenciaForm();
        model.addAttribute(MODELO, asistencia);
        model.addAttribute(RESULTADO, ESTADO_INICIAL);

        LOG.info("saliendo de modificarGET()");
        return MODIFICAR_ASISTENCIA;
    }

    @GetMapping("/mostrar")
    public String mostrar() {
        return MOSTRAR_ASISTENCIA;
    }

    @GetMapping("/eliminar")
    public String eliminar() {
        return ELIMINAR_ASISTENCIA;
    }
}
