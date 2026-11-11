package com.iuea.di.service;

import com.iuea.di.model.Student;
import org.springframework.stereotype.Service;

/**
 * CONSTRUCTOR INJECTION (recommended).
 * NotificationService does not create its MessageService with "new".
 * It simply declares what it needs; Spring supplies it.
 * Because the type is the interface, this class has no idea whether the
 * message goes out by email, SMS or anything else.
 *
 * Since Spring 4.3, @Autowired is optional when a class has a single constructor.
 */
@Service
public class NotificationService {

    private final MessageService messageService;   // final = cannot be changed after creation

    public NotificationService(MessageService messageService) {
        this.messageService = messageService;      // resolved to EmailMessageService (@Primary)
    }

    public void notifyStudent(Student student, String text) {
        messageService.send(student, "Dear " + student.name() + ", " + text);
    }
}
