package com.murgaloids.server.student;

import com.murgaloids.server.JsonWrapper;
import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

import com.murgaloids.server.security.Challenge;
import com.murgaloids.server.security.TagValidator;
import com.murgaloids.server.security.SecurityUtils;

/**
 * StudentController provides all the REST API relating to students.
 */
@RestController
@RequestMapping(path="/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public JsonWrapper<Student> addStudent(@NonNull @RequestBody Student student, HttpServletResponse res) {
        if (!studentRepository.existsByEmail(student.getEmail())) {
            studentRepository.save(student);
            res.addHeader(SecurityUtils.HEADER_STRING, SecurityUtils.TOKEN_PREFIX + " " + SecurityUtils.generateToken(student.getEmail()));
            return new JsonWrapper<>(student);
        }
        return null;
    }

    @GetMapping("/get")
    public @ResponseBody JsonWrapper<Student> getStudent(@NonNull @RequestParam Long id) {
        return new JsonWrapper<>(studentRepository.existsById(id) ? studentRepository.findById(id) : null);
    }

    @PostMapping("/get-challenge")
    public @ResponseBody Challenge getChallenge(@NonNull @RequestBody Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            Student existingStudent = studentRepository.findByEmail(student.getEmail());
            return new Challenge(existingStudent.getSalt());
        }
        return null;
    }

    @PostMapping("/validate-tag")
    public @ResponseBody JsonWrapper<Student> validateTag(@NonNull @RequestBody TagValidator theirTag, HttpServletResponse res) {
        if (studentRepository.existsByEmail(theirTag.getEmail())) {
            Student student = studentRepository.findByEmail(theirTag.getEmail());
            String password = student.getPassword();
            String ourTag = SecurityUtils.generateTag(theirTag.getChallenge(), password);

            if (ourTag != null && theirTag.getTag().equals(ourTag)) {
                res.addHeader(SecurityUtils.HEADER_STRING, SecurityUtils.TOKEN_PREFIX + " " + SecurityUtils.generateToken(student.getEmail()));
                return new JsonWrapper<>(student);
            }
        }
        return new JsonWrapper<>(null);
    }

    @GetMapping("/all")
    public @ResponseBody JsonWrapper<Iterable<Student>> getAllStudents() {
        return new JsonWrapper<>(studentRepository.findAll());
    }
}