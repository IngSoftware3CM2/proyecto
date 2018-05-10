package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.model.CambioHorarioModel;
import com.is.controlincidencias.service.CambioHorarioService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
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

@Controller
@RequestMapping("/personal/justificantes/cambiohorario") 
public class CambioHorarioController {
    static final String VISTA_CAMBIO_HORARIO = "justificanteCambioHorario/solicitud-cambio-horario";
    static final String VISTA_MOD_CAMBIO_HORARIO = "justificanteCambioHorario/modificar-cambio-horario";
    private static final Log LOGGER = LogFactory.getLog(CambioHorarioController.class);
    static final String VER_JUSTIFICANTES = "ver-justificantes";
    public static final String HORA_QUINCE = "15:00";

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioService cambioService;

    @GetMapping("/agregar")
    public String registrar(Model model)
        {
            LOGGER.info("Accedí al metodo acceder del controlador");
            model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
            return VISTA_CAMBIO_HORARIO;
        }

    @PostMapping("/add-cambio-horario")
    public ModelAndView addCambioHorario(@Valid @ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH, BindingResult bindings)
        {
            if(bindings.hasErrors())
                {
                    LOGGER.info("Hubo errores");
                    return new ModelAndView(VISTA_MOD_CAMBIO_HORARIO);
                }
            else
                {
                    LOGGER.info(modeloCH);
                    CambioHorarioModel chm = new CambioHorarioModel();
                    chm.setHoraEntrada("7:00"); //esto debería venir desde la base
                    chm.setHoraSalida(HORA_QUINCE); //esto igual
                    chm.setNuevaEntrada(modeloCH.getNuevaEntrada());
                    chm.setNuevaSalida(modeloCH.getNuevaSalida());
                    chm.setJustificacion(modeloCH.getJustificacion());
                    chm.setFechaIncidencia("10/10/2018");
                    chm.setIdJustificante(1);
                    cambioService.insertaCambioHorario(chm);
                    return new ModelAndView(VER_JUSTIFICANTES);
                }

        }


    @PostMapping("/mod-cambio-horario")
    public ModelAndView modCambioHorario(@Valid @ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH, BindingResult bindings)
    {
        if(bindings.hasErrors())
        {
            LOGGER.info("Hubo errores");
            return new ModelAndView(VISTA_CAMBIO_HORARIO);
        }
        else
        {
            LOGGER.info(modeloCH);
            CambioHorarioModel chm = new CambioHorarioModel();
            chm.setHoraEntrada("7:00"); //esto debería venir desde la base
            chm.setHoraSalida(HORA_QUINCE); //esto igual
            chm.setNuevaEntrada(modeloCH.getNuevaEntrada());
            chm.setNuevaSalida(modeloCH.getNuevaSalida());
            chm.setJustificacion(modeloCH.getJustificacion());
            chm.setFechaIncidencia("10/10/2018");
            chm.setIdJustificante(2);
            cambioService.updateCambioHorario(chm);
            return new ModelAndView(VER_JUSTIFICANTES);
        }

    }

    @GetMapping("/modificar")
    public ModelAndView modificaCambioHorario(@ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH)
    {
        LOGGER.info(modeloCH);
        CambioHorarioModel modch = new CambioHorarioModel();
        modch.setHoraEntrada("7:00");
        modch.setHoraSalida(HORA_QUINCE);
        modch.setIdJustificante(2);
        modch.setFechaIncidencia("10/11/2012");
        CambioHorario chEntidad = cambioService.getIdCambioHorario(2);//regresa entity
        //cambioService.findAllById(idCH);
        modch.setJustificacion(chEntidad.getJustificacion());
        modch.setNuevaSalida(chEntidad.getHoraSalida().toString());
        modch.setNuevaEntrada(chEntidad.getHoraEntrada().toString());
        ModelAndView mav = new ModelAndView(VISTA_MOD_CAMBIO_HORARIO);
        mav.addObject("nuevaEntrada", modch.getNuevaEntrada());
        mav.addObject("nuevaSalida", modch.getNuevaSalida());
        mav.addObject("justificacion", modch.getJustificacion());
        return mav;
    }
}
