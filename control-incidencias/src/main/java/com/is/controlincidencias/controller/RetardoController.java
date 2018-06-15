package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.CambioHorarioModel;
import com.is.controlincidencias.model.RetardoModel;
import com.is.controlincidencias.service.CambioHorarioService;
import com.is.controlincidencias.service.RetardoService;
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
@RequestMapping("/personal/justificantes/retardo")
public class RetardoController {
    static final String VISTA_RETARDO = "justificanteRetardo/retardo";
    static final String VISTA_MOD_RETARDO = "justificanteRetardo/mod-retardo";
    private static final Log LOGGER = LogFactory.getLog(RetardoController.class);


    int idEmpleado;
    int idIncidencia;
    int modCambHorarioJust;
    String fesha;
    int idJustGlobal = 0; //si modifica, esta agarra valor

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioService cambioService;


    @Autowired
    @Qualifier("retardoServiceImpl")
    private RetardoService retardoService;


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
        fesha = fecha;
        ModelAndView mav = new ModelAndView(VISTA_RETARDO);
        idIncidencia = idincidencia;
        idEmpleado = cambioService.getIdEmpleadoByIdIncidencia(idincidencia); //obtengo el numero de empelado
        LOGGER.info("El id de la incidencia es " + idincidencia + " el ID EMPLEADO es " + idEmpleado);
        model.addAttribute("retardoModel", new CambioHorarioModel());
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

    @PostMapping("/addRetardo")
    public String addCambioHorario(@Valid @ModelAttribute("retardoModel") RetardoModel modeloR, BindingResult bindings)
    {

            LOGGER.info("******************* ID Empleado es *********** " + idEmpleado);
            RetardoModel rm = new RetardoModel();
            rm.setJustificacion(modeloR.getJustificacion());
            rm.setIdJustificante(idEmpleado); //aqui meto el idEmpleado para enviarselo an repository
         //   cambioService.insertaCambioHorario(chm, idIncidencia);
            retardoService.addRetardo(rm, idIncidencia, fesha);
            return "redirect:/personal/justificantes";

    }


    @PostMapping("/modRetardo")
    public String modRetardo(@Valid @ModelAttribute("cambioHorarioModel") RetardoModel modeloR, BindingResult bindings)
    {
        retardoService.updateJust(modeloR.getJustificacion(), idJustGlobal);
        return "redirect:/personal/justificantes";

    }

    @GetMapping("/modificar")
    public ModelAndView modificaRetardo(Model model, @RequestParam(name="id")Integer idJustificante)
    {
        idJustGlobal = idJustificante;
        int idempleado = incidenciaService.getIdEmpleadoByIdJustificante(idJustificante);
        CambioHorario entCH = cambioService.getIdCambioHorario(idJustificante);
        String fecha = entCH.getFecha().toString();
        Personal personal = personalService.getPersonalByIdEmpleado(idempleado);
        ModelAndView mav = new ModelAndView(VISTA_MOD_RETARDO);
        String diaSemana = "";
        diaSemana = getDiaSemana(fecha);


        model.addAttribute("cambioHorarioModel", new CambioHorarioModel());
        LOGGER.info("Y vale **** " + personal.nombreAndTipoToString());
        modCambHorarioJust = idJustificante;
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        model.addAttribute("horarioEntrada", cambioService.getHoraE(idEmpleado, diaSemana));
        model.addAttribute("horarioSalida", cambioService.getHoraS(idEmpleado, diaSemana));
        model.addAttribute("tarjeta", personal.getNoTarjeta());
        model.addAttribute("fecha", fecha);
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
            e.printStackTrace();
        }
        return new SimpleDateFormat("EEEE", new Locale("es","ES")).format(date).toUpperCase().substring(0,2);
    }
}
