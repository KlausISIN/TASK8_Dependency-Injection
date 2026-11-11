package com.iuea.di.service;

import com.iuea.di.repository.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * The top-level business class. It depends on FOUR collaborators, all supplied
 * through its constructor. StudentService never calls "new" on any of them,
 * so it is not tied to a particular repository or messaging channel.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final NotificationService notificationService;
    private final UrgentAlertService urgentAlertService;
    private final AuditService auditService;

    public StudentService(StudentRepository studentRepository,
                          NotificationService notificationService,
                          UrgentAlertService urgentAlertService,
                          AuditService auditService) {
        this.studentRepository = studentRepository;
        this.notificationService = notificationService;
        this.urgentAlertService = urgentAlertService;
        this.auditService = auditService;
    }

    public void announceToAllStudents(String announcement) {
        auditService.log("Announcement broadcast started");
        studentRepository.findAll()
                .forEach(student -> notificationService.notifyStudent(student, announcement));
    }

    public void sendFeeReminder(int studentId) {
        studentRepository.findById(studentId).ifPresentOrElse(
                student -> urgentAlertService.raiseAlert(student, "Your fee balance is due on Friday."),
                () -> System.out.println("Student not found: " + studentId));
        auditService.log("Fee reminder processed for student " + studentId);
    }
}
