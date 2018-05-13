package com.is.controlincidencias;

import com.is.controlincidencias.controller.LicenciaPaternidadController;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
public class ControlIncidenciasApplication {
    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LOG.info("hola = " +  encoder.encode("hola"));
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }

}