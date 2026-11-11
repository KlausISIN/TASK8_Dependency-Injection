package com.iuea.di.service;

import com.iuea.di.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @Service  -> registers this class as a Spring bean (business/service layer).
 * @Primary  -> when several MessageService beans exist, Spring injects THIS one
 *              by default, unless the injection point asks for another with @Qualifier.
 */
@Service
@Primary
public class EmailMessageService implements MessageService {

    private final String universityName;

    // @Value injects a value from application.properties (configuration injection).
    public EmailMessageService(@Value("${university.name}") String universityName) {
        this.universityName = universityName;
    }

    @Override
    public void send(Student student, String content) {
        System.out.println("[EMAIL | " + universityName + "] To: " + student.email() + " | " + content);
    }
}
