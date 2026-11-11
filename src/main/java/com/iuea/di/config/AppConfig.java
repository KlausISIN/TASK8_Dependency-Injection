package com.iuea.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.format.DateTimeFormatter;

/**
 * Java-based configuration for the Spring container.
 *
 * @Configuration   -> this class defines beans and container settings.
 * @ComponentScan   -> tells Spring to scan com.iuea.di for @Service, @Repository, @Component...
 * @PropertySource  -> loads application.properties so @Value can read from it.
 * @Bean            -> manually registers an object we cannot annotate ourselves
 *                     (e.g. a class from the JDK or a third-party library).
 */
@Configuration
@ComponentScan(basePackages = "com.iuea.di")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public DateTimeFormatter auditDateFormatter() {
        return DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    }
}
