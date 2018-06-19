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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            String diaSemana = "";
            Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
            String fecha = incidencia.getFechaRegistro().toString();
            diaSemana = getDiaSemana(fecha);
            LOGGER.info("--------------------------------" + diaSemana);

            ModelAndView mav = new ModelAndView(VISTA_CAMBIO_HORARIO);
            LOGGER.info("Accedí al metodo acceder del controlador");
            idIncidencia = idincidencia;
            LOGGER.info("La variable global vale " + idIncidencia);
            idEmpleado = cambioService.getIdEmpleadoByIdIncidencia(idincidencia); //obtengo el numero de empelado
            LOGGER.info("El id de la incidencia es " + idincidencia + " el ID EMPLEADO es " + idEmpleado);
            model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
            Personal personal = personalService.getPersonalByIdEmpleado(idEmpleado);
            mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
            model.addAttribute("tarjeta", personal.getNoTarjeta());
            model.addAttribute("fecha", fecha);
            model.addAttribute("horarioEntrada", cambioService.getHoraE(idEmpleado, diaSemana));
            model.addAttribute("horarioSalida", cambioService.getHoraS(idEmpleado, diaSemana));
            model.addAttribute("horae", cambioService.getHoraEntrada(idEmpleado, fecha));
            model.addAttribute("horas", cambioService.getHoraSalida(idEmpleado, fecha));
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

                    LOGGER.info("******************* ID Empleado es *********** " + idEmpleado);
                    CambioHorarioModel chm = new CambioHorarioModel();

                    //Éstas cuatro cosas se sacan de la BD
                    chm.setHoraEntrada(""); //esto debería venir desde la base
                    chm.setHoraSalida(""); //esto igual
                    String fecha =  modeloCH.getFechaIncidencia();

                    chm.setNuevaEntrada(cambioService.getHoraEntrada(idEmpleado, fecha));
                    chm.setNuevaSalida(cambioService.getHoraSalida(idEmpleado, fecha));

                    chm.setJustificacion(modeloCH.getJustificacion());
                    chm.setFechaIncidencia(modeloCH.getFechaIncidencia());

                    chm.setIdJustificante(idEmpleado); //aqui meto el idEmpleado para enviarselo an repository
                    cambioService.insertaCambioHorario(chm, idIncidencia);
                    return "redirect:/personal/justificantes?add=1";
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
            chm.setIdJustificante(modCambHorarioJust);
            chm.setJustificacion(modeloCH.getJustificacion());
            cambioService.updateCambioHorario(chm);
            return "redirect:/personal/justificantes?add=1";
        }

    }

    @GetMapping("/modificar")
    public ModelAndView modificaCambioHorario(Model model, @RequestParam(name="id")Integer idJustificante)
    {
        int idempleado = incidenciaService.getIdEmpleadoByIdJustificante(idJustificante);
        CambioHorario entCH = cambioService.getIdCambioHorario(idJustificante);
        String fecha = entCH.getFecha().toString();
        Personal personal = personalService.getPersonalByIdEmpleado(idempleado);
        ModelAndView mav = new ModelAndView(VISTA_MOD_CAMBIO_HORARIO);
        String diaSemana = "";
        diaSemana = getDiaSemana(fecha);

        LOGGER.info("ID empleado es " + idempleado);
        model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
        LOGGER.info("Y vale **** " + personal.nombreAndTipoToString());
        modCambHorarioJust = idJustificante;
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        model.addAttribute("horarioEntrada", cambioService.getHoraE(idEmpleado, diaSemana));
        model.addAttribute("horarioSalida", cambioService.getHoraS(idEmpleado, diaSemana));
        model.addAttribute("tarjeta", personal.getNoTarjeta());
        model.addAttribute("fecha", fecha);

        LOGGER.info("**********tengo " + entCH.getHoraEntrada().toString() + " y tambien " + entCH.getHoraSalida() + " Y... " + entCH.getJustificacion());
        model.addAttribute("horae", entCH.getHoraEntrada().toString());
        model.addAttribute("horas", entCH.getHoraSalida());
        model.addAttribute("justificacion", entCH.getJustificacion());
        return mav;
    }
    public String getDiaSemana(String fechaCompleta)
        {
            Date date = null;
            try
            {
                date = new SimpleDateFormat("yyyy-M-d").parse(fechaCompleta);
            }
            catch (ParseException e)
            {
                LOGGER.error(e);
            }
            return new SimpleDateFormat("EEEE", new Locale("es","ES")).format(date).toUpperCase().substring(0,2);
        }
}
