package org.siri_hate.user_service.configuration;

import org.siri_hate.user_service.security.JWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for setting up security measures in the application.
 * It enables web security and method security features and defines beans
 * required for authentication and authorization.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    /**
     * Constructs a SecurityConfiguration instance with the provided UserDetailsService.
     *
     * @param userDetailsService Service responsible for loading user-specific data.
     */
    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the security filter chain with HTTP security configurations and a JWT authentication filter.
     *
     * @param http         HttpSecurity instance to configure security settings.
     * @param jwtAuthFilter JWT authentication filter to be applied in the security filter chain.
     * @return SecurityFilterChain instance configured with specified security rules.
     * @throws Exception If configuration of HttpSecurity fails.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTAuthFilter jwtAuthFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                               .requestMatchers("/api/v1/users/member/check_token").authenticated()
                                               .requestMatchers("/api/v1/users/member/delete_my_account").authenticated()
                                               .requestMatchers("/api/v1/users/member/change_personal_info").authenticated()
                                               .requestMatchers("/api/v1/users/member/change_password").authenticated()
                                               .requestMatchers("/api/v1/user_service/members/change_profile_visibility").authenticated()
                                               .anyRequest().permitAll()
                                      )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Defines a PasswordEncoder bean for encoding and verifying passwords.
     *
     * @return Instance of BCryptPasswordEncoder for password encoding.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines an AuthenticationProvider bean for authenticating users.
     *
     * @param passwordEncoder Password encoder used to encode passwords.
     * @return Instance of DaoAuthenticationProvider configured with UserDetailsService and PasswordEncoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    /**
     * Defines an AuthenticationManager bean for managing authentication processes.
     *
     * @param config AuthenticationConfiguration instance required to obtain AuthenticationManager.
     * @return AuthenticationManager instance retrieved from AuthenticationConfiguration.
     * @throws Exception If authentication manager cannot be obtained.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
