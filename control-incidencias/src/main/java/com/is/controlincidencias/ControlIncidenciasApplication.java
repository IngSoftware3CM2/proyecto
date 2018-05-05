package com.is.controlincidencias;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ControlIncidenciasApplication {
    private static final Log LOG = LogFactory.getLog(ControlIncidenciasApplication.class);

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LOG.info("Hola = " +  encoder.encode("hola"));
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }

}


/* ESTA CLASE ES PARA EL DEPLOY, NO LA BORREN PORQUE SE ME OLVIDA
* @SpringBootApplication
*public class ControlIncidenciasApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ControlIncidenciasApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }

}
*
*
* */