package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.model.PaaeModel;
import com.is.controlincidencias.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dch/usuarios")
public class UsuariosController {
    private static final String REGISTRAR_DOCENTE = "dch/registrar-docente";
    private static final String REGISTRAR_PAAE = "dch/registrar-paae";
    private static final String MODIFICAR_USUARIO = "dch/usuarios-modificar";
    private static final String DEPARTAMENTOS = "departamentos";
    private static final String REDIRECT_HOME = "redirect:/dch";

    private static final int TIPO_PAEE = 1;
    private static final int TIPO_DOC = 2;

    private static final int HORAS_SEMANA_INVALIDO = 1; // Trayectoria I

    @Autowired
    @Qualifier("usuariosServiceImpl")
    private UsuariosService usuariosService;

    @GetMapping({"", "/"})
    public String inicio() {
        return REDIRECT_HOME;
    }

    @GetMapping("/registrar")
    public String registrarRedirect() {
        return "redirect:/dch/usuarios/registrar/docente";
    }

    @GetMapping("/registrar/docente")
    public String mostrarVistaDocente() {
        return REGISTRAR_DOCENTE;
    }

    @GetMapping("/registrar/paae")
    public String mostarVistaPaae(Model model) {
        PaaeModel paaeModel = new PaaeModel();
        List<LocalTime> horas = new ArrayList<>();
        LocalTime temp = LocalTime.of(8, 0);
        while (temp.isBefore(LocalTime.of(22, 30))) {
            horas.add(temp);
            temp = temp.plusMinutes(30);
        }
        model.addAttribute("modeloPAAE", paaeModel);
        model.addAttribute("horas", horas);
        model.addAttribute(DEPARTAMENTOS, usuariosService.recuperarDepartamentos(TIPO_PAEE));
        return REGISTRAR_PAAE;
    }

    @PostMapping("/registrar/paae")
    public String registrarPaae(@Valid @ModelAttribute("modeloPAAE") PaaeModel paaeModel,
            BindingResult bindingResult, Model model) {
        log.info("registrarPaae() Se recibio: " + paaeModel.toString());
        int horas = paaeModel.getLunes().getHoraSalida().toSecondOfDay() - paaeModel.getLunes()
                .getHoraEntrada().toSecondOfDay();
        horas = horas / 3600;
        if (horas == 7 || horas == 6) {
            log.info("CHIDO");
            usuariosService.registarPaee(paaeModel);
        } else
            log.info("NO TAN CHIDO");

        if (bindingResult.hasErrors()){
            model.addAttribute(DEPARTAMENTOS, usuariosService.recuperarDepartamentos(TIPO_PAEE));
            return REGISTRAR_PAAE;
        }
        return REDIRECT_HOME;
    }

    @GetMapping("/modificar")
    public String modificar() {
        return MODIFICAR_USUARIO;
    }
}
