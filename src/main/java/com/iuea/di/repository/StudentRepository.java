package com.iuea.di.repository;

import com.iuea.di.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * Abstraction for student data access.
 * Dependent classes talk to this interface, never to a concrete class,
 * so the storage (memory, MySQL, MongoDB...) can be swapped without touching them.
 */
public interface StudentRepository {

    List<Student> findAll();

    Optional<Student> findById(int id);
}
