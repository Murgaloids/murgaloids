package com.murgaloids.server.security;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * This class extends the WebSecurityConfigurerAdapter class so that it can take
 * advantage of the default web security configuration provided by Spring Security.
 * This allows us to fine-tune the framework the our needs by overriding the methods.
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    // Defines which resources are public and which are private. Also, configure support
    // for CORS.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel()
            .antMatchers(SecurityUtils.SIGN_UP_URL).requiresSecure()
            .antMatchers(SecurityUtils.GET_CHALLENGE_URL).requiresSecure()
            .antMatchers(SecurityUtils.VALIDATE_TAG_URL).requiresSecure()
            .antMatchers(SecurityUtils.SOCKET_INFO_URL).requiresSecure()
            .antMatchers(SecurityUtils.SOCKET_URL).requiresSecure()
            .antMatchers(SecurityUtils.SOCKET_URL_2).requiresSecure()
            .and().cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(SecurityUtils.SIGN_UP_URL).permitAll()
            .antMatchers(SecurityUtils.GET_CHALLENGE_URL).permitAll()
            .antMatchers(SecurityUtils.VALIDATE_TAG_URL).permitAll()
            .antMatchers(SecurityUtils.SOCKET_INFO_URL).permitAll()
            .antMatchers(SecurityUtils.SOCKET_URL).permitAll()
            .antMatchers(SecurityUtils.SOCKET_URL_2).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // Allows/restricts our CORS support. In our case we left it wide open by permitting
    // request from any source "(/**)".
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("Access-Control-Expose-Headers");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("If-None-Match");
        config.addAllowedHeader("Access-Control-Allow-Headers");

        config.addExposedHeader("Access-Control-Allow-Origin");
        config.addExposedHeader("Access-Control-Allow-Headers");
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}