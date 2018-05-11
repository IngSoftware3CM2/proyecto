package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.model.JefeForm;
import com.is.controlincidencias.model.PaaeForm;
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
import java.util.function.Consumer;

@Slf4j
@Controller
@RequestMapping("/dch/usuarios")
public class UsuariosController {
    private static final String REGISTRAR_DOCENTE = "dch/registrar-docente";
    private static final String REGISTRAR_PAAE = "dch/registrar-paae";
    private static final String REGISTRAR_ADMON = "dch/registrar-admon";
    private static final String REGISTRAR_JEFE = "dch/registrar-jefe";
    private static final String MODIFICAR_USUARIO = "dch/usuarios-modificar";
    private static final String DEPARTAMENTOS = "departamentos";

    @Autowired
    @Qualifier("usuariosServiceImpl")
    private UsuariosService usuariosService;

    @GetMapping({"", "/"})
    public String inicio() {
        return "redirect:/dch";
    }

    @GetMapping("/registrar")
    public String registrarRedirect() {
        return "redirect:/dch/usuarios/registrar/docente";
    }

    @GetMapping("/registrar/docente")
    public String registrarDocente() {
        return REGISTRAR_DOCENTE;
    }

    @GetMapping("/registrar/paae")
    public String registrarPaaeGET(Model model) {
        model.addAttribute("modeloPAAE", new PaaeForm());
        model.addAttribute(DEPARTAMENTOS, usuariosService.recuperarDepartamentos());
        return REGISTRAR_PAAE;
    }

    @PostMapping("/registrar/paae")
    public String registrarPaaePOST(@Valid @ModelAttribute("modeloPAAE") PaaeForm paaeForm,
            BindingResult bindingResult, Model model) {
        log.info("registrarPAAEPOST() Se recibio: " + paaeForm.toString());
        if (bindingResult.hasErrors()){
            model.addAttribute(DEPARTAMENTOS, usuariosService.recuperarDepartamentos());
            return REGISTRAR_PAAE;
        }
        return "redirect:/dch";
    }

    @GetMapping("/registrar/admon")
    public String registrarAdmon() {
        return REGISTRAR_ADMON;
    }

    @GetMapping("/registrar/jefe")
    public String registrarJefeGET(Model model) {
        log.info("registrarJefeGET()");
        model.addAttribute("modeloJefe", new JefeForm());
        model.addAttribute(DEPARTAMENTOS, usuariosService.recuperarDepartamentos());

        return REGISTRAR_JEFE;
    }

    @PostMapping("/registrar/jefe")
    public String registrarJefePOST(@Valid @ModelAttribute("modeloJefe") JefeForm jefeForm,
            BindingResult bindingResult, Model model) {
        log.info("registrarJefePOST() Se recibio: " + jefeForm.toString());
        if (bindingResult.hasErrors()){
            model.addAttribute(DEPARTAMENTOS, usuariosService.recuperarDepartamentos());
            return REGISTRAR_JEFE;
        }
        return "redirect:/dch";
    }

    @GetMapping("/modificar")
    public String modificar() {
        return MODIFICAR_USUARIO;
    }
}
