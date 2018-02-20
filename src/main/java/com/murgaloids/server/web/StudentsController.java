package com.murgaloids.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.murgaloids.server.Student;
import com.murgaloids.server.StudentRepository;

@RestController
@RequestMapping(path="/students")
public class StudentsController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewStudent(
        @RequestParam String email,
        @RequestParam String firstName,
        @RequestParam String lastName
    ) {
        Student student = new Student(email, firstName, lastName);
        studentRepository.save(student);
        return "Saved!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}