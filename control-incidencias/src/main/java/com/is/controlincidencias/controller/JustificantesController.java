package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
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
    @Qualifier("justificanteServiceImpl")
    private JustificanteServiceImpl justificanteService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

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
        Personal personalJustificante = new Personal();
        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());
        model.addAttribute("personal", personalJustificante);
        model.addAttribute("entradaRegistrada", 1);
        model.addAttribute("salidaRegistrada", 1);
        model.addAttribute("entradaHorario", 1);
        model.addAttribute("salidaHorario", 1);
        model.addAttribute("idJustificante", idJustificante);
        model.addAttribute("departamento", "Justificacion");
        model.addAttribute("fecha", "Justificacion");
        model.addAttribute("justificacion", "Justificacion");




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
        return "redirect:/justificantes/validar";
    }



    @GetMapping("/cambiohorario")
    public String verCambioHorario(@RequestParam(name = "id") Integer idJustificante, Principal
            principal, Model model) {
        log.info("verCambioHorario()");
        String email = "abhera@yandex.com";
        Integer esCH = 1; // Uno para mostrar la barra de superior
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        Personal personal = personalService.getPersonalByEmail(email);
        if (personal.getTipo().equals("ROLE_CH"))
            esCH = 2;
        model.addAttribute("tipo_usuario", esCH);
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());



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

        Personal personalJustificante = new Personal();
        Incidencia incidencia = new Incidencia();
        model.addAttribute("nombreYtipo", personal.nombreAndTipoToString());

        if (personal.getTipo().equals("ROLE_DOC"))
            personal.setTipo("Docente");
        else if (personal.getTipo().equals("ROLE_DCADM"))
            personal.setTipo("Docente Administrativo");
        else if (personal.getTipo().equals("PAAE"))
            personal.setTipo("PAAE");

        model.addAttribute("personal", personalJustificante);
        model.addAttribute("departamento", personal.getDepartamento().getNombre());
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
        return "redirect:/justificantes/validar";
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
        return "redirect:/justificantes/validar";
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

    @GetMapping("/redirect}")
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
    public ModelAndView validarJustificantes (Principal principal) {
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
                if (j.getPersonal().getDepartamento().equals(personal.getDepartamento())){
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
            for (Justificante j : showJustificantes) {
                if (j.getTipo() == 2){
                    showJustificantes.remove(j);
                }
            }
        }
        //SUBDIRECTOR
        else if (personal.getTipo().equals("ROLE_SUB")) {
            for (Justificante j : allJustificantes) {
                if (j.getTipo() == 6 || j.getTipo() == 8){
                    if (j.getPersonal().getDepartamento().equals(personal.getDepartamento())) {
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

        return mav;
    }


}
