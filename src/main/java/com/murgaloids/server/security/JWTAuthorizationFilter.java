package com.murgaloids.server.security;

import io.jsonwebtoken.Jwts;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for authorizing users. It extends the BasicAuthenticationFilter class so that
 * it can override the method, doFilterInternal, to fit our needs when we add it to the filter class.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    // Extracts the JWTf from the header and parses it to see if user is validated or not.
    @Override
    protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain
    ) throws IOException, ServletException {
        String header = req.getHeader(SecurityUtils.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityUtils.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityUtils.HEADER_STRING);
        if (token != null) {
            // Parse the token.
            String student = Jwts.parser()
                .setSigningKey(SecurityUtils.SECRET.getBytes())
                .parseClaimsJws(token.replace(SecurityUtils.TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

            if (student != null)
                return new UsernamePasswordAuthenticationToken(student, null, new ArrayList<>());

            return null;
        }

        return null;
    }
}