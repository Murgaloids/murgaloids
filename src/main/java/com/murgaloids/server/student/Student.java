package com.murgaloids.server.student;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Student is a class representation of a CSULB student.
 */
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "email", columnDefinition = "varchar(254)")
    private String email;

    @Column(name = "first_name", columnDefinition = "varchar(25)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(25)")
    private String lastName;

    @Column(name="password", columnDefinition = "varchar(100)")
    private String password;

    @Column(name = "salt", columnDefinition = "varchar(100)")
    private String salt;

    @Column(name = "description", columnDefinition = "varchar(254)")
    private String description;

    @Column(name = "image_source", columnDefinition = "varchar(254)")
    private String imageSource;

    protected Student() {}

    public Student(Long id, String email, String firstName, String lastName, String password, String salt, String description) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.salt = salt;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSource() {
        return this.imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}