package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.OmisionModel;
import com.is.controlincidencias.service.CambioHorarioService;
import com.is.controlincidencias.service.impl.IncidenciaServiceImpl;
import com.is.controlincidencias.service.impl.OmisionESServiceImpl;
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
        static final String VISTA_MOD_OMISION_ES =  "justificanteOmisionES/mod-omision-es.html";
        private static final Log LOGGER = LogFactory.getLog(OmisionESController.class);
        int idEmpleado;
        int idIncidencia;
        String fesha = "0000-00-00";
        int idJustGlobal = 0; //si modifica, esta agarra valor

        @Autowired
        @Qualifier("cambioHorarioServiceImpl")
        private CambioHorarioService cambioService;

        @Autowired
        @Qualifier("incidenciaServiceImpl")
        private IncidenciaServiceImpl incidenciaService;

        @Autowired
        @Qualifier("personalServiceImpl")
        private PersonalServiceImpl personalService;

        @Autowired
        @Qualifier("omisionServiceImpl")
        private OmisionESServiceImpl omisionService;

        @GetMapping("/agregar")
        public ModelAndView registrar(Model model, @RequestParam(name="id")Integer idincidencia)
        {
            String diaSemana = "";
            String horaE = "";
            String horaS = "";
            Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
            String fecha = incidencia.getFechaRegistro().toString();
            fesha = fecha;
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
            setTipos(model, horaS, horaE);

            model.addAttribute("horarioEntrada", cambioService.getHoraE(idEmpleado, diaSemana));
            model.addAttribute("horarioSalida", cambioService.getHoraS(idEmpleado, diaSemana));


            return mav;
        }

        @PostMapping("/addOmision")
        public String addOmision(@Valid @ModelAttribute("omisionModel") OmisionModel modeloES, BindingResult bindings)
        {
                OmisionModel om = new OmisionModel();
                om.setJustificacion(modeloES.getJustificacion());
                om.setIdJustificante(idEmpleado); //aqui meto el idEmpleado para enviarselo an repository
                om.setTipo(modeloES.isTipo());
                omisionService.addOmision(om, idIncidencia, fesha);
                return "redirect:/personal/justificantes?add=1";
        }



        @PostMapping("/modOmision")
        public String modOmision(@Valid @ModelAttribute("omisionModel") OmisionModel modeloES)
        {
            omisionService.updateJust(modeloES.getJustificacion(), idJustGlobal);
            return "redirect:/personal/justificantes?add=1";
        }


        @GetMapping("/modificar")
        public ModelAndView modificar(Model model,  @RequestParam(name="id")Integer idJustificante)
        {
            String diaSemana = "";
            String horaE = "";
            String horaS = "";
            idJustGlobal = idJustificante;
            String fecha = omisionService.getFecha(idJustificante);
            diaSemana = getDiaSemana(fecha);
            LOGGER.info("--------------------------------" + diaSemana);
            int idempleado = incidenciaService.getIdEmpleadoByIdJustificante(idJustificante);
            ModelAndView mav = new ModelAndView(VISTA_MOD_OMISION_ES);
            model.addAttribute("omisionModel", new OmisionModel());
            Personal personal = personalService.getPersonalByIdEmpleado(idempleado);
            mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
            model.addAttribute("tarjeta", personal.getNoTarjeta());
            model.addAttribute("fecha", fecha);
            horaE = cambioService.getHoraEntrada(idempleado, fecha);
            horaS = cambioService.getHoraSalida(idempleado, fecha);
            LOGGER.info("****************//////////////////////////////// Tengo" + horaE + " - " + horaS);
            setTipos(model, horaS, horaE);
            model.addAttribute("horarioEntrada", cambioService.getHoraE(idempleado, diaSemana));
            model.addAttribute("horarioSalida", cambioService.getHoraS(idempleado, diaSemana));
            model.addAttribute("justificacion", omisionService.getJust(idJustificante));
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
                LOGGER.info("Error en el get dia semana", e);
            }
            return new SimpleDateFormat("EEEE", new Locale("es","ES")).format(date).toUpperCase().substring(0,2);
        }

        public void setTipos(Model model, String horaS, String horaE)
            {
                boolean tipo;
                if(horaE.equals("00:00:00")) //hora entrada omitida, false
                {
                    tipo = false;
                    model.addAttribute("horae", "N/A");
                    model.addAttribute("horas", horaS);
                    model.addAttribute("tipoes", "Entrada");
                }
                else
                {
                    tipo = true;
                    model.addAttribute("horae", horaE);
                    model.addAttribute("horas", "N/A");
                    model.addAttribute("tipoes", "Salida");
                }
                model.addAttribute("tipo", tipo);
            }
    }

