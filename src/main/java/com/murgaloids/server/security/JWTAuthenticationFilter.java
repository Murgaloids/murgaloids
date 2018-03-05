package com.murgaloids.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import com.murgaloids.server.student.Student;

/**
 * This class is responsible for the authentication process. It extends the UsernamePasswordAuthenticationFilter
 * class so that it can override the methods, attemptAuthentication and successfulAuthentication, to fit our
 * needs when we add them to the filter chain.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Parses the user's credentials and issue them to the AuthenticationManager.
    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest req,
        HttpServletResponse res
    ) throws AuthenticationException {
        try {
            Student student = new ObjectMapper().readValue(req.getInputStream(), Student.class);
            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    student.getEmail(),
                    student.getPassword(),
                    new ArrayList<>()
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets called when a user successfully logs in. We use this method to generate a JWT for the user.
     */
    @Override
    protected void successfulAuthentication(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain,
        Authentication auth
    ) throws IOException, ServletException {
        String student = ((User) auth.getPrincipal()).getUsername();
        res.addHeader(SecurityUtils.HEADER_STRING, SecurityUtils.TOKEN_PREFIX + " " + SecurityUtils.generateToken(student));
    }
}