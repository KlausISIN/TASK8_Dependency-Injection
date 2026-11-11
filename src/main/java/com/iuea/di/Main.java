package com.iuea.di;

import com.iuea.di.config.AppConfig;
import com.iuea.di.service.NotificationService;
import com.iuea.di.service.ReportService;
import com.iuea.di.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== Spring Dependency Injection =====\n");

        // Start the IoC container: it reads AppConfig, scans for beans,
        // creates them and injects every dependency before we use anything.
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            // We only ask for the top-level bean. Its dependencies are already wired.
            StudentService studentService = context.getBean(StudentService.class);

            System.out.println("--- 1. Announcement (constructor injection, @Primary email channel) ---");
            studentService.announceToAllStudents("Semester 1 examinations begin on Monday.");

            System.out.println("\n--- 2. Fee reminder (@Qualifier SMS channel) ---");
            studentService.sendFeeReminder(2);

            System.out.println("\n--- 3. Report (field injection) ---");
            context.getBean(ReportService.class).printSummary();

            System.out.println("\n--- 4. Singleton scope check ---");
            NotificationService first = context.getBean(NotificationService.class);
            NotificationService second = context.getBean(NotificationService.class);
            System.out.println("Same NotificationService instance? " + (first == second));
        }
    }
}
