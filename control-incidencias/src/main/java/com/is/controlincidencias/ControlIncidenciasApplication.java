package com.is.controlincidencias;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControlIncidenciasApplication implements CommandLineRunner {
    public static final Log LOG = LogFactory.getLog(ControlIncidenciasApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("HOLA");
    }
}
