package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.impl.JustificanteServiceImpl;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
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
    public String verPaternidad(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/tipoa")
    public String verTipoA(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/omision")
    public String verOmision(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/retardo")
    public String verRetardo(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/cambiohorario")
    public String verCambioHorario(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/economico")
    public String verEconomico(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/suplementario")
    public String verSuplementario(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/comision")
    public String verComisionOficial(@RequestParam(name = "id") Integer idJustificante) {
        return "inicio";
    }

    @GetMapping("/redirect}")
    public String redirectJustificante(@RequestParam(name = "id") Integer id,
            @RequestParam(name = "tipo") Integer tipo, RedirectAttributes attributes) {
        log.info("redirectJustificante() id = " + id + " tipoJustificante = " + tipo);
        attributes.addAttribute("id", id);
        String redirectURL = "redirect:/personal";
        // Faltan los demás pero no se los numeros
        if (tipo == 1)
            redirectURL = "redirect:/justifiantes/tipoa";
        else if (tipo == 2)
            redirectURL = "";

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
                }
            }
            for (Justificante j : showJustificantes) {
                if (j.getTipo() == 6 || j.getTipo() == 8){
                    showJustificantes.remove(j);
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

        mav.addObject("justificantes", allJustificantes);

        /* Para mostrar la barra vertical correcta
         * Mandar 2 para capital humano 1 para cualquier otro superior
         * obviamente depende de quien haya iniciado sesion (su ROL)
         */
        mav.addObject("tipo_usuario", esCH);

        return mav;
    }


}
