package com.is.controlincidencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class ControlIncidenciasApplication {

    public static void main(String[] args) {
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