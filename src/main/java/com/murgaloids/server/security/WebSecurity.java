package com.murgaloids.server.security;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
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
    private UserDetailsService userDetailsService;

    public WebSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Defines which resources are public and which are private. Also, configure support
    // for CORS.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, SecurityUtils.SIGN_UP_URL).permitAll()
            .antMatchers(HttpMethod.POST, SecurityUtils.GET_CHALLENGE_URL).permitAll()
            .antMatchers(HttpMethod.POST, SecurityUtils.VALIDATE_TAG_URL).permitAll()
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
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}