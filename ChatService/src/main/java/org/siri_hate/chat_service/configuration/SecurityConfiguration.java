package org.siri_hate.chat_service.configuration;

import org.siri_hate.chat_service.security.JWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is a configuration class for Spring Security.
 * It sets up the security filter chain and configures the authentication and authorization mechanisms.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    final private JWTAuthFilter jwtAuthFilter;

    /**
     * Constructor for the SecurityConfiguration class.
     * It initializes the JWTAuthFilter.
     *
     * @param jwtAuthFilter the JWT authentication filter
     */
    @Autowired
    public SecurityConfiguration(JWTAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * This method configures the security filter chain.
     * It disables CSRF and CORS, permits all requests and adds the JWT authentication filter.
     *
     * @param http the HttpSecurity to modify
     * @return the configured SecurityFilterChain
     * @throws Exception in case of any errors
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                               .anyRequest().permitAll()
                                      )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * This method provides a BCryptPasswordEncoder bean.
     * The BCryptPasswordEncoder is used for password encoding.
     *
     * @return a new BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This method provides the current Authentication object.
     * The Authentication object represents the currently authenticated user.
     *
     * @return the current Authentication object
     */
    @Bean
    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}