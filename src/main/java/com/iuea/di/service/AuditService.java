package com.iuea.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SETTER INJECTION.
 * Spring creates the object first, then calls the annotated setter to inject the dependency.
 * Suitable for optional or re-configurable dependencies.
 * The DateTimeFormatter bean is defined in AppConfig using @Bean.
 */
@Service
public class AuditService {

    private DateTimeFormatter formatter;

    @Autowired
    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public void log(String action) {
        System.out.println("[AUDIT " + LocalDateTime.now().format(formatter) + "] " + action);
    }
}
