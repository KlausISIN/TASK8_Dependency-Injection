package com.iuea.di.model;

/**
 * A simple immutable data class (Java record) representing a student.
 * Records automatically provide the constructor, getters (id(), name(), ...),
 * equals(), hashCode() and toString().
 */
public record Student(int id, String name, String email, String phone) {
}
