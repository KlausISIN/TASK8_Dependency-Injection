package com.iuea.di.service;

import com.iuea.di.model.Student;
import org.springframework.stereotype.Service;

/**
 * A second implementation of MessageService.
 * The explicit bean name "smsMessageService" is what @Qualifier refers to.
 */
@Service("smsMessageService")
public class SmsMessageService implements MessageService {

    @Override
    public void send(Student student, String content) {
        System.out.println("[SMS] To: " + student.phone() + " | " + content);
    }
}
