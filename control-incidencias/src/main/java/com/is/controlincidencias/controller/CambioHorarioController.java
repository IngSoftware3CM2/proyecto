package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.CambioHorarioModel;
import com.is.controlincidencias.service.CambioHorarioService;
import com.is.controlincidencias.service.impl.IncidenciaServiceImpl;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
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
@RequestMapping("/personal/justificantes/cambiohorario")
public class CambioHorarioController {
    static final String VISTA_CAMBIO_HORARIO = "justificanteCambioHorario/solicitud-cambio-horario";
    static final String VISTA_MOD_CAMBIO_HORARIO = "justificanteCambioHorario/modificar-cambio-horario";
    private static final Log LOGGER = LogFactory.getLog(CambioHorarioController.class);
    static final String VER_JUSTIFICANTES = "ver-justificantes";

    int idEmpleado;
    int idIncidencia;
    int modCambHorarioJust;
    public static final String HORA_QUINCE = "15:00";

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioService cambioService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaServiceImpl incidenciaService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

    @GetMapping("/agregar")
    public ModelAndView registrar(Model model, @RequestParam(name="id")Integer idincidencia)
        {
            ModelAndView mav = new ModelAndView(VISTA_CAMBIO_HORARIO);
            LOGGER.info("Accedí al metodo acceder del controlador");
            idIncidencia = idincidencia;
            idEmpleado = cambioService.getIdEmpleadoByIdIncidencia(idincidencia); //obtengo el numero de empelado
            LOGGER.info("El id de la incidencia es " + idincidencia + " el ID EMPLEADO es " + idEmpleado);
            model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
            Personal personal = personalService.getPersonalByIdEmpleado(idEmpleado);
            mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
            Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
            model.addAttribute("tarjeta", personal.getNoTarjeta().toString());
            model.addAttribute("fecha", incidencia.getFechaRegistro().toString());
            return mav;
        }

    @PostMapping("/addCambioHorario")
    public String addCambioHorario(@Valid @ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH, BindingResult bindings)
        {
            if(bindings.hasErrors())
                {
                    LOGGER.info("Hubo errores");
                    return VISTA_CAMBIO_HORARIO;
                }
            else
                {
                    LOGGER.info(modeloCH);
                    LOGGER.info("******************* ID Empleado es *********** " + idEmpleado);
                    CambioHorarioModel chm = new CambioHorarioModel();
                    chm.setHoraEntrada("7:00"); //esto debería venir desde la base
                    chm.setHoraSalida(HORA_QUINCE); //esto igual
                    chm.setNuevaEntrada(modeloCH.getNuevaEntrada());
                    chm.setNuevaSalida(modeloCH.getNuevaSalida());
                    chm.setJustificacion(modeloCH.getJustificacion());
                    chm.setFechaIncidencia("10/10/2018");
                    chm.setIdJustificante(idEmpleado); //aqui meto el idEmpleado para enviarselo an repository
                    cambioService.insertaCambioHorario(chm, idIncidencia);
                    return "redirect:/personal/justificantes";
                }

        }


    @PostMapping("/modCambioHorario")
    public String modCambioHorario(@Valid @ModelAttribute("cambioHorarioModel") CambioHorarioModel modeloCH, BindingResult bindings)
    {
        if(bindings.hasErrors())
        {
            LOGGER.info("Hubo errores");
            return VISTA_MOD_CAMBIO_HORARIO;
        }
        else
        {
            LOGGER.info("MODIFICANDO :3 *3*");
            LOGGER.info(modeloCH);
            CambioHorarioModel chm = new CambioHorarioModel();
            chm.setHoraEntrada("7:00"); //esto debería venir desde la base
            chm.setHoraSalida(HORA_QUINCE); //esto igual
            chm.setNuevaEntrada(modeloCH.getNuevaEntrada());
            chm.setNuevaSalida(modeloCH.getNuevaSalida());
            chm.setIdJustificante(modCambHorarioJust);
            chm.setJustificacion(modeloCH.getJustificacion());
            cambioService.updateCambioHorario(chm);
            return "redirect:/personal/justificantes";
        }

    }

    @GetMapping("/modificar")
    public ModelAndView modificaCambioHorario(Model model, @RequestParam(name="id")Integer idJustificante)
    {
        int idempleado = incidenciaService.getIdEmpleadoByIdJustificante(idJustificante);
        CambioHorario entCH = cambioService.getIdCambioHorario(idJustificante);
        Personal personal = personalService.getPersonalByIdEmpleado(idempleado);
        ModelAndView mav = new ModelAndView(VISTA_MOD_CAMBIO_HORARIO);
        LOGGER.info("ID empleado es " + idempleado);
        model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
        LOGGER.info("Y vale **** " + personal.nombreAndTipoToString());
        modCambHorarioJust = idJustificante;
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        model.addAttribute("tarjeta", personal.getNoTarjeta().toString());
        model.addAttribute("fecha", entCH.getFecha().toString());
        LOGGER.info("**********tengo " + entCH.getHoraEntrada().toString() + " y tambien " + entCH.getHoraSalida() + " Y... " + entCH.getJustificacion());
        model.addAttribute("nuevaEntrada", entCH.getHoraEntrada().toString());
        model.addAttribute("nuevaSalida", entCH.getHoraSalida());
        model.addAttribute("justificacion", entCH.getJustificacion());
        return mav;
    }
}
