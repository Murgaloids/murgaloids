package com.murgaloids.server.student;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static java.util.Collections.emptyList;

/**
 * StudentService provides methods to manipulate and retrieve student entities that
 * was stored in the MySQL database.
 */
@Service
public class StudentService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    // When a user tries to authenticate themselves, this method will receive their
    // email, search it within the database, and (if found) returns an instance of
    // User. The properties of this instance (username and password) are then checked
    // against the credentials passed in by the user in the login request.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        if (student == null)
            throw new UsernameNotFoundException(email);

        return new User(student.getEmail(), student.getPassword(), emptyList());
    }
}