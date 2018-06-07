package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.OmisionModel;
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
@RequestMapping("/personal/justificantes/omision")
public class OmisionESController
    {
        static final String VISTA_OMISION_ES = "justificanteOmisionES/omision-es.html";
        static final String VISTA_MOD_OMISION_ES = "";
        private static final Log LOGGER = LogFactory.getLog(OmisionESController.class);
        int idEmpleado;
        int idIncidencia;

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
            String horaE = "";
            String horaS = "";
            Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
            String fecha = incidencia.getFechaRegistro().toString();
            diaSemana = getDiaSemana(fecha);
            LOGGER.info("--------------------------------" + diaSemana);

            ModelAndView mav = new ModelAndView(VISTA_OMISION_ES);
            idIncidencia = idincidencia;
            idEmpleado = cambioService.getIdEmpleadoByIdIncidencia(idincidencia); //obtengo el numero de empleado
            model.addAttribute("omisionModel", new OmisionModel());
            Personal personal = personalService.getPersonalByIdEmpleado(idEmpleado);
            mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
            model.addAttribute("tarjeta", personal.getNoTarjeta());
            model.addAttribute("fecha", fecha);
            horaE = cambioService.getHoraEntrada(idEmpleado, fecha);
            horaS = cambioService.getHoraSalida(idEmpleado, fecha);
            LOGGER.info("****************//////////////////////////////// Tengo" + horaE + " - " + horaS);
            boolean tipo;
            if(horaE.equals("00:00:00")) //hora entrada omitida, false
            {
                tipo = false;
                model.addAttribute("horae", "N/A");
                model.addAttribute("horas", horaS);
            }
            else
            {
                tipo = true;
                model.addAttribute("horae", horaE);
                model.addAttribute("horas", "N/A");
            }

            model.addAttribute("horarioEntrada", cambioService.getHoraE(idEmpleado, diaSemana));
            model.addAttribute("horarioSalida", cambioService.getHoraS(idEmpleado, diaSemana));
            model.addAttribute("tipoe", !tipo);
            model.addAttribute("tipos", tipo);

            return mav;
        }

        @PostMapping("/addOmision")
        public String addCambioHorario(@Valid @ModelAttribute("cambioHorarioModel") OmisionModel modeloES, BindingResult bindings)
        {
                LOGGER.info("******************* ID Empleado es *********** " + idEmpleado);
                OmisionModel om = new OmisionModel();
                om.setJustificacion(modeloES.getJustificacion());
                om.setIdJustificante(idEmpleado); //aqui meto el idEmpleado para enviarselo an repository
                om.setTipo(modeloES.isTipo());
             //   cambioService.insertaCambioHorario(om, idIncidencia);
                return "redirect:/personal/justificantes";
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
                LOGGER.info("Error en el get dia semana", e);
            }
            return new SimpleDateFormat("EEEE", new Locale("es","ES")).format(date).toUpperCase().substring(0,2);
        }

    }

