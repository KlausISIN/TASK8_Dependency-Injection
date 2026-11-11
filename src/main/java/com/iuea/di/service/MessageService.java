package com.iuea.di.service;

import com.iuea.di.model.Student;

/**
 * The "contract" for any communication channel (email, SMS, WhatsApp...).
 * Classes that need to send messages depend on THIS interface, not on a
 * concrete implementation. That is what gives us loose coupling.
 */
public interface MessageService {

    void send(Student student, String content);
}
