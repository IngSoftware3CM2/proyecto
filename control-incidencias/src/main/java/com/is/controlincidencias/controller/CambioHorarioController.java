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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("personal/justificantes/horario")
public class CambioHorarioController {
    static final String VISTA_CAMBIO_HORARIO = "justificanteCambioHorario/solicitud-cambio-horario";
    static final String VISTA_MOD_CAMBIO_HORARIO = "justificanteCambioHorario/modificar-cambio-horario";
    private static final Log LOGGER = LogFactory.getLog(CambioHorarioController.class);
    static final String VER_JUSTIFICANTES = "ver-justificantes";
    int noEmpleado, idIncidencia;

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioService cambioService;

    @GetMapping("/agregar")
    public String registrar(Model model, @RequestParam(name="id")Integer idincidencia)
        {
            LOGGER.info("Accedí al metodo acceder del controlador");
            idIncidencia = idincidencia;
            noEmpleado = cambioService.getNoEmpleadoByIdIncidencia(idincidencia); //obtengo el numero de empelado para todo *3*
            LOGGER.info("El id de la incidencia es " + idincidencia + " el ID EMPLEADO es " + noEmpleado);
            model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
            return VISTA_CAMBIO_HORARIO;
        }

    @PostMapping("/addCambioHorario")
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
                    LOGGER.info("******************* Num Empleado es *********** " + noEmpleado);
                    CambioHorarioModel chm = new CambioHorarioModel();
                    chm.setHoraEntrada("7:00"); //esto debería venir desde la base
                    chm.setHoraSalida("15:00"); //esto igual
                    chm.setNuevaEntrada(modeloCH.getNuevaEntrada());
                    chm.setNuevaSalida(modeloCH.getNuevaSalida());
                    chm.setJustificacion(modeloCH.getJustificacion());
                    chm.setFechaIncidencia("10/10/2018");
                    chm.setIdJustificante(noEmpleado); //aqui meto el noEmpleado para enviarselo an repository
                    cambioService.insertaCambioHorario(chm, idIncidencia);
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
            chm.setHoraSalida("15:00"); //esto igual
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
        modch.setHoraSalida("15:00");
        modch.setIdJustificante(2);
        modch.setFechaIncidencia("10/11/2012");
        CambioHorario chEntidad = cambioService.getIdCambioHorario(2);//regresa entity
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
