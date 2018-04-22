package com.is.controlincidencias.controller;

import com.is.controlincidencias.model.LicPaternidadModel;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/licenciapaternidad")

public class LicenciaPaternidadController {



    /*@Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;*/

    @GetMapping("/form")
    private String RedirectSolicitudLicenciaPaternidadForm(Model model){
        return "solicitud-licencia-paternidad-chafa";
    }

    @PostMapping("/add-lic-paternidad")
    private String GuardarLicPaternidad(@ModelAttribute(name = "licPaternidadModel") LicPaternidadModel licPaternidadModel){
        return  null;
    }

}
