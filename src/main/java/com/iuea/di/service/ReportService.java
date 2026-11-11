package com.iuea.di.service;

import com.iuea.di.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FIELD INJECTION.
 * Spring sets the private field directly using reflection.
 * It is short, but NOT recommended for real projects: the field cannot be final,
 * dependencies are hidden, and the class cannot be built with "new" in a unit test
 * without Spring. It is included here only to compare the three injection styles.
 */
@Service
public class ReportService {

    @Autowired
    private StudentRepository studentRepository;

    public void printSummary() {
        System.out.println("Total registered students: " + studentRepository.findAll().size());
    }
}
