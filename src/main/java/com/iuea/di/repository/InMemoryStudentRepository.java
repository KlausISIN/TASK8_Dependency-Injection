package com.iuea.di.repository;

import com.iuea.di.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Repository marks this class as a Spring bean (data-access layer).
 * Component scanning finds it and the container creates ONE instance (singleton).
 */
@Repository
public class InMemoryStudentRepository implements StudentRepository {

    private final List<Student> students = List.of(
            new Student(1, "Grace Namukasa", "grace.namukasa@example.com", "+256700000001"),
            new Student(2, "David Okello", "david.okello@example.com", "+256700000002"),
            new Student(3, "Brian Atuhaire", "brian.atuhaire@example.com", "+256700000003")
    );

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Optional<Student> findById(int id) {
        return students.stream().filter(s -> s.id() == id).findFirst();
    }
}
