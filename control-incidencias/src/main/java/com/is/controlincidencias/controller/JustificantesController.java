package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.*;
import com.is.controlincidencias.repository.AsistenciaRepository;
import com.is.controlincidencias.repository.DiaRepository;
import com.is.controlincidencias.service.OmisionESService;
import com.is.controlincidencias.service.AsistenciaService;
import com.is.controlincidencias.service.CambioHorarioService;
import com.is.controlincidencias.service.RetardoService;
import com.is.controlincidencias.service.impl.IncidenciaServiceImpl;
import com.is.controlincidencias.service.impl.JustificanteServiceImpl;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Para no tener un desmadre con las redirecciones la vizualizacion y la validacion se
 * trabajaran en este controlador al cual redireccionaran los respectivas vistas de ver
 * justificantes */

@Slf4j
@Controller
@RequestMapping("/justificantes")
public class JustificantesController {

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaServiceImpl incidenciaService;

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioService cambioService;

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteServiceImpl justificanteService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;


    @Autowired
    @Qualifier("diaRepository")
    private DiaRepository diaRepository;

    @Autowired
    @Qualifier("asistenciaServiceImpl")
    private AsistenciaService asistenciaService;

    @Autowired
    @Qualifier("retardoServiceImpl")
    private RetardoService retardoService;

    @Autowired
    @Qualifier("omisionServiceImpl")
    private OmisionESService omisionESService;

    @Autowired
    @Qualifier("asistenciaRepository")
    private AsistenciaRepository asistenciaRepository;

    @GetMapping("/paternidad")
    public String verPaternidad(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verPaternidad()");

        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;

        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());

        return "justificantes/paternidad";
    }

    @GetMapping("/tipoa")
    public String verTipoA(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verTipoA()");

        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;
        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());


        return "justificantes/tipoa";
    }

    @GetMapping("/omision")
    public String verOmision(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verOmision()");
        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;
        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());

        Personal personalJustificante = personalService.getPersonalByIdJustificante(idJustificante);
        Incidencia incidencia = incidenciaService.obtenerIncidenciaPorJustificanteId(idJustificante);
        OmisionEntrSal omisionEntrSal = omisionESService.getOmisionByIdJustificante(idJustificante);

        Asistencia a = asistenciaRepository.findAsistenciaByFechaRegistroAndPersonalNoTarjeta
                (incidencia.getFechaRegistro(), personalJustificante.getNoTarjeta());


        // Obtiene el horario de dicho empleado del dia con la fecha registrada en la incidencia
        String diaSemana = obtenerDia(incidencia.getFechaRegistro());
        HorarioActual h = personal.getHorarioActual();
        Integer idHorario = h.getIdHorario();
        Dia dia = diaRepository.findFirstByHorarioActual_IdHorarioAndNombre(idHorario,
                diaSemana);

        if (personalJustificante.getTipo().equals("ROLE_DOC"))
            personalJustificante.setTipo("Docente");
        else if (personalJustificante.getTipo().equals("ROLE_DCADM"))
            personalJustificante.setTipo("Docente Administrativo");
        else if (personalJustificante.getTipo().equals("PAAE"))
            personalJustificante.setTipo("PAAE");

        model.addAttribute("personal", personalJustificante);
        model.addAttribute("departamento", personalJustificante.getDepartamento().getNombre());
        model.addAttribute("fecha", incidencia.getFechaRegistro());
        model.addAttribute("justificacion", omisionEntrSal.getJustificacion());

        // A partir del idempleado del justificante obtener los siguintes datos para mostrarlos en la pantalla
        model.addAttribute("horarioEntrada", dia.getHoraEntrada() );
        model.addAttribute("horarioSalida", dia.getHoraSalida() );
        model.addAttribute("horaEntrada", a.getHoraEntrada() );
        model.addAttribute("horaSalida",a.getHoraSalida() );

        // El tipo de omision
        String tipoOmision = omisionEntrSal.getTipo() == true ? "Salida" :"Entrada";
        model.addAttribute("tipoOmision", tipoOmision);

        model.addAttribute("idJustificante", idJustificante);

        return "justificantes/omision";
    }

    @GetMapping("/retardo")
    public String verRetardo(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verRetardo()");
        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;
        Personal personalJustificante = personalService.getPersonalByIdJustificante(idJustificante);
        Incidencia incidencia = incidenciaService.obtenerIncidenciaPorJustificanteId(idJustificante);
        HorarioActual h = personalJustificante.getHorarioActual();
        Integer idHorario = h.getIdHorario();
        String diaNombre = IncidenciaServiceImpl.obtenerDia(incidencia.getFechaRegistro());
        Dia dia = diaRepository.findFirstByHorarioActual_IdHorarioAndNombre(idHorario, diaNombre);
        if (dia == null)
            dia = new Dia();
        Asistencia asistencia = asistenciaService
                .buscarAsistenciaPorFechaTarjeta(personalJustificante.getNoTarjeta(),
                        incidencia.getFechaRegistro());

        if (asistencia == null)
            asistencia = new Asistencia();

        String justificacion = retardoService.getJust(idJustificante);

        if (personalJustificante.getTipo().equals("ROLE_DOC")) {
            personalJustificante.setTipo("Docente");
        } else if (personalJustificante.getTipo().equals("ROLE_DCADM"))
            personalJustificante.setTipo("Docente Administrativo");
        else if (personalJustificante.getTipo().equals("ROLE_PAAE"))
            personalJustificante.setTipo("PAAE");

        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());
        model.addAttribute("personal", personalJustificante);
        model.addAttribute("entradaRegistrada", asistencia.getHoraEntrada());
        model.addAttribute("salidaRegistrada", asistencia.getHoraSalida());
        model.addAttribute("entradaHorario", dia.getHoraEntrada());
        model.addAttribute("salidaHorario", dia.getHoraSalida());

        model.addAttribute("idJustificante", idJustificante);
        model.addAttribute("departamento", personalJustificante.getDepartamento().getNombre());
        model.addAttribute("fecha", incidencia.getFechaRegistro());
        model.addAttribute("justificacion", justificacion);

        return "justificantes/retardo";
    }

    @GetMapping("/retardo/aceptar")
    public String aceptarRetardo(@RequestParam(name = "id") Integer id, Principal principal) {
        log.info("aceptarRetardo() id=" + id);
        String email = "abhera@yandex.com";
        if (principal != null && principal.getName() != null)
            email = principal.getName();

        Personal personal = personalService.getPersonalByEmail(email);
        aceptarEconomicoRetardoCambioHorarioSuplementario(personal, id);
        return "redirect:/justificantes/validar?resultado=1";
    }



    @GetMapping("/cambiohorario")
    public String verCambioHorario(@RequestParam(name = "id") Integer idJustificante, Principal principal, Model model) {

        int idEmpleado = incidenciaService.getIdEmpleadoByIdJustificante(idJustificante);
        CambioHorario entCH = cambioService.getIdCambioHorario(idJustificante);
        String fecha = entCH.getFecha().toString();
        Personal personal = personalService.getPersonalByIdEmpleado(idEmpleado);
        String diaSemana = "";
        diaSemana = (new CambioHorarioController()).getDiaSemana(fecha);
        CambioHorario entidadCH = cambioService.getIdCambioHorario(idJustificante);

        log.info("verCambioHorario()");
        String email = "abhera@yandex.com";
        String tipeishon  = "";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
       // Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            {
                esCH = 2;
                tipeishon = "Capital Humano";
            }
         if(personal.getTipo().equals("ROLE_DOC"))
            {
                tipeishon = "Docente";
            }
        if(personal.getTipo().equals("ROLE_PAAE"))
            {
                tipeishon = "PAAE";
            }
        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());
        model.addAttribute("tipoP", tipeishon);
        model.addAttribute("depto",cambioService.getDepto(personal.getDepartamento().getIdDepartamento()));
        model.addAttribute("nombre",personal.getNombre() + " " + personal.getApellidoPaterno() + " " + personal.getApellidoPaterno());
        model.addAttribute("tarjeta", personal.getNoTarjeta());
        model.addAttribute("fecha", fecha);
        model.addAttribute("jornadaE", cambioService.getHoraE(idEmpleado, diaSemana));
        model.addAttribute("jornadaS", cambioService.getHoraS(idEmpleado, diaSemana));
        model.addAttribute("llegadaE", cambioService.getHoraEntrada(idEmpleado, fecha));
        model.addAttribute("llegadaS", cambioService.getHoraSalida(idEmpleado, fecha));
        model.addAttribute("just", entidadCH.getJustificacion());

        return "justificantes/chorario";
    }

    @GetMapping("/economico")
    public String verEconomico(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verEconomico()");
        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();

        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;
        model.addAttribute("tipo_usuario", esCH);

        Personal personalJustificante = personalService.getPersonalByIdJustificante(idJustificante);
        Incidencia incidencia = incidenciaService.obtenerIncidenciaPorJustificanteId(idJustificante);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());

        if (personalJustificante.getTipo().equals("ROLE_DOC"))
            personalJustificante.setTipo("Docente");
        else if (personalJustificante.getTipo().equals("ROLE_DCADM"))
            personalJustificante.setTipo("Docente Administrativo");
        else if (personalJustificante.getTipo().equals("PAAE"))
            personalJustificante.setTipo("PAAE");

        model.addAttribute("personal", personalJustificante);
        model.addAttribute("departamento", personalJustificante.getDepartamento().getNombre());
        model.addAttribute("fecha", incidencia.getFechaRegistro());
        model.addAttribute("idJustificante", idJustificante);
        return "justificantes/economico";
    }

    @GetMapping("/economico/aceptar")
    public String aceptarEconomico(@RequestParam(name = "id") Integer id, Principal principal) {
        log.info("aceptarEconomico() id=" + id);
        String email = "abhera@yandex.com";
        if (principal != null && principal.getName() != null)
            email = principal.getName();

        Personal personal = personalService.getPersonalByEmail(email);
        aceptarEconomicoRetardoCambioHorarioSuplementario(personal, id);
        return "redirect:/justificantes/validar?resultado=1";
    }

    private void aceptarEconomicoRetardoCambioHorarioSuplementario(Personal personal, Integer id) {
        if (personal.getTipo().equals("ROLE_CH"))
            justificanteService.cambiarEstadoJustificante(id, 1); // Aceptado CH
        else
            justificanteService.cambiarEstadoJustificante(id, 2); // Aceptado por jefe y director
    }
    /*
     * Este metodo puede ser usado por cualquier justificante pero pregunten para ver como se va a
     * manejar
     * */
    @GetMapping("/todos/rechazar")
    public String rechazarEconomico(@RequestParam(name = "id") Integer id) {
        log.info("rechazarEconomico() id=" + id);
        justificanteService.cambiarEstadoJustificante(id, 0);
        return "redirect:/justificantes/validar?resultado=2";
    }

    @GetMapping("/suplementario")
    public String verSuplementario(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verSuplementario()");
        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;
        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());



        return "justificantes/suplementario";
    }

    @GetMapping("/comision")
    public String verComisionOficial(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verComisionOficial()");
        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;
        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());



        return "justificantes/coficial";
    }

    @GetMapping("/redirect")
    public String redirectJustificante(@RequestParam(name = "id") Integer id,
                                       @RequestParam(name = "tipo") Integer tipo, RedirectAttributes attributes) {
        log.info("redirectJustificante() id = " + id + " tipoJustificante = " + tipo);
        attributes.addAttribute("id", id);
        String redirectURL = "redirect:/personal";
        if (tipo == 1)
            redirectURL = "redirect:/justificantes/tipoa";
        else if (tipo == 2)
            redirectURL = "redirect:/justificantes/paternidad";
        else if (tipo == 3)
            redirectURL = "redirect:/justificantes/cambiohorario";
        else if (tipo == 4)
            redirectURL = "redirect:/justificantes/economico";
        else if (tipo == 5)
            redirectURL = "redirect:/justificantes/suplementario";
        else if (tipo == 6)
            redirectURL = "redirect:/justificantes/omision";
        else if (tipo == 7)
            redirectURL = "redirect:/justificantes/retardo";
        else if (tipo == 8)
            redirectURL = "redirect:/justificantes/comision";

        return redirectURL;
    }


    @GetMapping("/validar")
    public ModelAndView validarJustificantes (Principal principal, @RequestParam(name =
            "resultado", required = false) Integer resultado) {
        ModelAndView mav = new ModelAndView("validar-justificantes");
        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();


        List<Justificante> allJustificantes = new ArrayList<>();
        List<Justificante> showJustificantes = new ArrayList<>();
        allJustificantes = justificanteService.getAllJustificante();
        Personal personal = personalService.getPersonalByEmail(email);

        for (Justificante j : allJustificantes) {
            log.info("FECHAJUST: "+j.getFechaAsString());
        }

        //CAPITAL HUMANO
        if (personal.getTipo().equals("ROLE_CH")){
            esCH = 2; // Dos para mostrar la barra de CH
            for (Justificante j : allJustificantes) {
                if (j.getEstado() == 2){
                    showJustificantes.add(j);
                }
            }
        }
        //DIRECTOR
        else if (personal.getTipo().equals("ROLE_DIR")) {
            for (Justificante j : allJustificantes) {
                if (j.getEstado() == 3){
                    showJustificantes.add(j);
                    System.out.println("Justificante " + j.getTipo());
                }
            }


            for (Iterator<Justificante> it = showJustificantes.iterator(); it.hasNext();) {
                Justificante j = it.next();
                if (j.getTipo() == 6 || j.getTipo() == 8){
                    System.out.println("Justificante " + j.getTipo());
                    it.remove();
                }
            }
        }
        //JEFE DE DEPARTAMENTO
        else if (personal.getTipo().equals("ROLE_SUP")) {
            for (Justificante j : allJustificantes) {
                if (j.getPersonal().getDepartamento().getIdDepartamento() == personal.getDepartamento().getIdDepartamento()){
                    if (j.getTipo() == 6 || j.getTipo() == 8){
                        if (j.getEstado() == 4){
                            showJustificantes.add(j);
                        }
                    }
                    else{
                        if (j.getEstado() == 3){
                            showJustificantes.add(j);
                        }
                    }

                }
            }

            for (Iterator<Justificante> it = showJustificantes.iterator(); it.hasNext();) {
                Justificante j = it.next();
                if (j.getTipo() == 2){
                    System.out.println("Justificante " + j.getTipo());
                    it.remove();
                }
            }
        }
        //SUBDIRECTOR
        else if (personal.getTipo().equals("ROLE_SUB")) {
            for (Justificante j : allJustificantes) {
                if (j.getTipo() == 6 || j.getTipo() == 8){
                    if (j.getPersonal().getDepartamento().getIdDepartamento() == personal.getDepartamento().getIdDepartamento()) {
                        if (j.getEstado() == 3){
                            showJustificantes.add(j);
                        }
                    }
                }
            }
        }
        //SUBDIRECTOR ADMINISTRATIVO
        else if (personal.getTipo().equals("ROLE_ADM")) {
            for (Justificante j : allJustificantes) {
                if (j.getTipo() == 2 && j.getEstado() == 4){
                    showJustificantes.add(j);
                }
            }
        }

        //MANDANDO JUSTIFICANTES DE ACUERDO A ROL Y NOMBRE Y TIPO DEL USUARIO
        mav.addObject("justificantes", showJustificantes);
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());

        //PRINTS PARA PRUEBAS
        System.out.println("JUSTIFICANTES A MANDAR: \n\n");
        for (Justificante j: showJustificantes) {
            System.out.println("tipo: " +j.getTipo() +" estado: " + j.getEstado().intValue());
        }

        /* Para mostrar la barra vertical correcta
         * Mandar 2 para capital humano 1 para cualquier otro superior
         * obviamente depende de quien haya iniciado sesion (su ROL)
         */
        mav.addObject("tipo_usuario", esCH);
        mav.addObject("resultado", resultado);
        return mav;
    }


    @GetMapping("/omision/aceptar")
    public String aceptarOmision(@RequestParam(name = "id") Integer id, Principal principal) {
        log.info("aceptarOmision() id=" + id);
        String email = "abhera@yandex.com";
        if (principal != null && principal.getName() != null)
            email = principal.getName();

        Personal personal = personalService.getPersonalByEmail(email);
        aceptarEconomicoRetardoCambioHorarioSuplementario(personal, id);
        return "redirect:/justificantes/validar";
    }

    private String obtenerDia(LocalDate fecha) {
        switch (fecha.getDayOfWeek()) {
            case MONDAY:
                return "LUN";
            case TUESDAY:
                return "MAR";
            case WEDNESDAY:
                return "MIE";
            case THURSDAY:
                return "JUE";
            case FRIDAY:
                return "VIE";
            default:
                return "END";
        }
    }

}
