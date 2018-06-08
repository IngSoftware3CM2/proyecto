package com.is.controlincidencias.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders
        .AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration
        .EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration
        .WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /*
     * En este metodo se debe de comentar el codigo enorme que tiene
     * el comentario de CON LOGIN y descomentar el que dice
     * SIN LOGIN para poder utilizar el proyecto sin tener que iniciar sesion
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable(); // SIN LOGIN
        http.csrf().disable(); // Necesario para peticiones ajax, luego checo como meterle mas
        // seguridad
/*
        http.authorizeRequests().antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**",
                "/font-awesome/**").permitAll();
        http.authorizeRequests().antMatchers("/dch/**").hasRole("DCH")
                .antMatchers("/personal/**").hasAnyRole("DOC", "PAAE")
                .and().formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/loginsuccess").permitAll().and().logout().logoutUrl("/logout")
<<<<<<< HEAD
                .logoutSuccessUrl("/login?logout").permitAll();*/  // CON LOGIN
=======
                .logoutSuccessUrl("/login?logout").permitAll();  // CON LOGIN
                */
>>>>>>> a16781c1c9b9aab37a82b1e6845440e5cf19fc3f

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
