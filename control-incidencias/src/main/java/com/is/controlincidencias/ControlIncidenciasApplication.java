package com.is.controlincidencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
@SpringBootApplication
public class ControlIncidenciasApplication {
    private static final Log LOG = LogFactory.getLog(ControlIncidenciasApplication.class);

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LOG.info("Hola = " +  encoder.encode("hola"));
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }

}
*/

/* ESTA CLASE ES PARA EL DEPLOY, NO LA BORREN PORQUE SE ME OLVIDA*/

@SpringBootApplication
public class ControlIncidenciasApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ControlIncidenciasApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }
}
