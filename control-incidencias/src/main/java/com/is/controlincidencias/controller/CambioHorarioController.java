package com.is.controlincidencias.controller;

import com.is.controlincidencias.model.CambioHorarioModel;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cambio-horario")
public class CambioHorarioController {
    static final String VISTA_CAMBIO_HORARIO = "solicitud-cambio-horario";
    static final String COMFIRMAR_CAMBIO_HORARIO = "ver-cambio-horario";
    private static final Log LOGGER = LogFactory.getLog(IniciarSesionController.class);

    @GetMapping("/registrar")
    public String registrar(Model model)
        {
            LOGGER.info("Accedí al metodo acceder del controlador");
            model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
            return VISTA_CAMBIO_HORARIO;
        }

    @GetMapping("/comfirmar")
    public String comfirmar() { return COMFIRMAR_CAMBIO_HORARIO; }

    @PostMapping("/add-cambio-horario")
    public ModelAndView addCambioHorario(@ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH)
        {
            LOGGER.info(modeloCH);
            ModelAndView mav = new ModelAndView(COMFIRMAR_CAMBIO_HORARIO);
            return mav;
        }
}
