package org.siri_hate.main_service.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a filter that is applied once per request.
 * It is responsible for validating the JWT token in the Authorization header of the request.
 * If the token is valid, it extracts the claims and sets the authentication in the SecurityContext.
 * If the token is not valid, it clears the SecurityContext.
 * It is annotated with @Component, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    /**
     * Constructor for JWTAuthFilter.
     *
     * @param jwtService the JWTService to use for token validation and claim extraction.
     */
    public JWTAuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * This method is called once per request.
     * It validates the JWT token in the Authorization header of the request.
     * If the token is valid, it extracts the claims and sets the authentication in the SecurityContext.
     * If the token is not valid, it clears the SecurityContext.
     *
     * @param request     the HttpServletRequest.
     * @param response    the HttpServletResponse.
     * @param filterChain the FilterChain.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
                                   ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                if (jwtService.validateToken(token)) {
                    Claims claims = jwtService.extractAllClaims(token);
                    if (claims != null) {
                        List<GrantedAuthority> authorities = extractAuthorities(claims);
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * This method extracts the authorities from the claims.
     * It splits the roles claim by comma and maps each role to a SimpleGrantedAuthority.
     *
     * @param claims the Claims from which to extract the authorities.
     * @return a List of GrantedAuthority.
     */
    private List<GrantedAuthority> extractAuthorities(Claims claims) {
        return Arrays.stream(claims.get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}