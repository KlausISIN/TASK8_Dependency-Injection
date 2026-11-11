package com.iuea.di.service;

import com.iuea.di.model.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Because NotificationService receives its dependency through the constructor,
 * we can test it WITHOUT starting Spring and WITHOUT sending a real email/SMS:
 * we just pass in a fake MessageService. This is the maintainability benefit of DI.
 */
class NotificationServiceTest {

    private final Student student = new Student(1, "Test Student", "test@example.com", "+256700000000");

    @Test
    void notifyStudent_usesTheInjectedChannel() {
        List<String> sent = new ArrayList<>();
        MessageService fakeChannel = (s, content) -> sent.add(content);

        NotificationService service = new NotificationService(fakeChannel);
        service.notifyStudent(student, "Hello");

        assertEquals(1, sent.size());
        assertEquals("Dear Test Student, Hello", sent.get(0));
    }

    @Test
    void sameServiceWorksWithDifferentChannels() {
        List<String> emailLog = new ArrayList<>();
        List<String> smsLog = new ArrayList<>();

        new NotificationService((s, c) -> emailLog.add(s.email())).notifyStudent(student, "Hi");
        new NotificationService((s, c) -> smsLog.add(s.phone())).notifyStudent(student, "Hi");

        assertEquals("test@example.com", emailLog.get(0));
        assertEquals("+256700000000", smsLog.get(0));
    }
}
