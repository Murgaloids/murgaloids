package com.murgaloids.server.student;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Student is a class representation of a CSULB student.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @NonNull
    @Column(name = "email", columnDefinition = "varchar(254)")
    private String email;

    @NonNull
    @Column(name = "first_name", columnDefinition = "varchar(25)")
    private String firstName;

    @NonNull
    @Column(name = "last_name", columnDefinition = "varchar(25)")
    private String lastName;

    @NonNull
    @Column(name="password", columnDefinition = "varchar(100)")
    private String password;

    @NonNull
    @Column(name = "salt", columnDefinition = "varchar(100)")
    private String salt;
}