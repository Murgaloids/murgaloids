package com.murgaloids.server;

import org.springframework.data.repository.CrudRepository;

import com.murgaloids.server.Student;

// This will be AUTO IMPLEMENTED by Spring into a Bean called studentRepository
public interface StudentRepository extends CrudRepository<Student, Long> {}