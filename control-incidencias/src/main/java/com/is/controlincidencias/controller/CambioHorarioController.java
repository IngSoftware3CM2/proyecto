package com.is.controlincidencias.controller;

import com.is.controlincidencias.model.CambioHorarioModel;
import com.is.controlincidencias.service.CambioHorarioService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cambio-horario")
public class CambioHorarioController {
    static final String VISTA_CAMBIO_HORARIO = "justificanteCambioHorario/solicitud-cambio-horario";
    private static final Log LOGGER = LogFactory.getLog(CambioHorarioController.class);
    static final String VER_JUSTIFICANTES = "ver-justificantes";

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioService cambioService;

    @GetMapping("/registrar")
    public String registrar(Model model)
        {
            LOGGER.info("Accedí al metodo acceder del controlador");
            model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
            return VISTA_CAMBIO_HORARIO;
        }

    @PostMapping("/add-cambio-horario")
    public ModelAndView addCambioHorario(@ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH)
        {
            LOGGER.info(modeloCH);
            CambioHorarioModel chm = new CambioHorarioModel();
            chm.setHoraEntrada("7:00");
            chm.setHoraSalida("15:00");
            chm.setNuevaEntrada(modeloCH.getNuevaEntrada());
            chm.setNuevaSalida(modeloCH.getNuevaSalida());
            chm.setJustificación(modeloCH.getJustificación());
            chm.setFechaIncidencia("10/10/2018");
            cambioService.insertaCambioHorario(chm);
            ModelAndView mav = new ModelAndView(VER_JUSTIFICANTES);
            return mav;
        }

    @PostMapping("/modifica-cambio-horario")
    public ModelAndView modificaCambioHorario(@ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH)
    {
        LOGGER.info(modeloCH);
        CambioHorarioModel modch = new CambioHorarioModel();
        modch.setHoraEntrada("7:00");
        modch.setHoraSalida("15:00");
        modch.setNuevaEntrada(modeloCH.getNuevaEntrada());
        modch.setNuevaSalida(modeloCH.getNuevaSalida());
        modch.setJustificación(modeloCH.getJustificación());
        modch.setFechaIncidencia("10/11/2012");
        ModelAndView mav = new ModelAndView(VER_JUSTIFICANTES);
        return mav;
    }
}
