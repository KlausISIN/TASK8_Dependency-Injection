package com.iuea.di.service;

import com.iuea.di.model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Same dependency TYPE (MessageService) as NotificationService, but a different
 * implementation. @Qualifier overrides @Primary and picks the bean by name.
 * This shows reusability: one interface, many implementations, chosen by configuration.
 */
@Service
public class UrgentAlertService {

    private final MessageService messageService;

    public UrgentAlertService(@Qualifier("smsMessageService") MessageService messageService) {
        this.messageService = messageService;
    }

    public void raiseAlert(Student student, String text) {
        messageService.send(student, "URGENT: " + text);
    }
}
