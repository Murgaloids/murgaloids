package com.murgaloids.server.student;

import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called studentRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
    boolean existsByEmail(String email);
    Student findByEmail(String email);
}