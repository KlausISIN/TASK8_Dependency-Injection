# Spring Dependency Injection Demo (Java 17, Maven, IntelliJ)

## Open in IntelliJ
1. Unzip the project.
2. IntelliJ: File > Open > select the `pom.xml` > "Open as Project".
3. Set the Project SDK to JDK 17 (File > Project Structure > Project) and wait for Maven to download dependencies.
4. Right-click `Main.java` > Run 'Main.main()'.
5. To run the tests: right-click `src/test/java` > Run 'All Tests'.

## Project structure
```
src/main/java/com/iuea/di
  Main.java                          starts the Spring container
  config/AppConfig.java              @Configuration, @ComponentScan, @PropertySource, @Bean
  model/Student.java                 record
  repository/StudentRepository.java  interface
  repository/InMemoryStudentRepository.java   @Repository
  service/MessageService.java        interface (the abstraction)
  service/EmailMessageService.java   @Service @Primary, @Value
  service/SmsMessageService.java     @Service("smsMessageService")
  service/NotificationService.java   constructor injection
  service/UrgentAlertService.java    constructor injection + @Qualifier
  service/AuditService.java          setter injection
  service/ReportService.java         field injection
  service/StudentService.java        constructor injection (4 dependencies)
src/main/resources/application.properties
src/test/java/com/iuea/di/service/NotificationServiceTest.java
```

## Expected output (AUDIT timestamps will differ)
```
===== Spring Dependency Injection Demo =====

--- 1. Announcement (constructor injection, @Primary email channel) ---
[AUDIT 11-Nov-2026 18:47:02] Announcement broadcast started
[EMAIL | International University of East Africa] To: grace.namukasa@example.com | Dear Grace Namukasa, Semester 1 examinations begin on Monday.
[EMAIL | International University of East Africa] To: david.okello@example.com | Dear David Okello, Semester 1 examinations begin on Monday.
[EMAIL | International University of East Africa] To: brian.atuhaire@example.com | Dear Brian Atuhaire, Semester 1 examinations begin on Monday.

--- 2. Fee reminder (@Qualifier SMS channel) ---
[SMS] To: +256700000002 | URGENT: Your fee balance is due on Friday.
[AUDIT 11-Nov-2026 18:47:02] Fee reminder processed for student 2

--- 3. Report (field injection) ---
Total registered students: 3

--- 4. Singleton scope check ---
Same NotificationService instance? true
```

## Where each DI concept is shown
| Concept | Where |
|---|---|
| Bean creation by scanning (@Service, @Repository) | all classes in `service/` and `repository/` |
| Bean creation by configuration (@Bean) | `AppConfig.auditDateFormatter()` |
| Constructor injection | `NotificationService`, `UrgentAlertService`, `StudentService` |
| Setter injection | `AuditService.setFormatter()` |
| Field injection | `ReportService` |
| @Primary vs @Qualifier | `EmailMessageService` vs `UrgentAlertService` |
| Property injection (@Value) | `EmailMessageService` constructor |
| Loose coupling | classes depend on `MessageService` / `StudentRepository` interfaces |
| Reusability | one `MessageService` interface, two implementations, used by two services |
| Maintainability / testability | `NotificationServiceTest` (no Spring needed) |
