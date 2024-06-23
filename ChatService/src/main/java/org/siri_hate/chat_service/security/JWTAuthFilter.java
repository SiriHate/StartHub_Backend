package org.siri_hate.chat_service.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a filter for JWT authentication.
 * It extends the OncePerRequestFilter class provided by Spring Security.
 * It contains methods to filter requests and extract authorities from JWT claims.
 */
@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    /**
     * The JWT service.
     */
    private final JWTService jwtService;

    /**
     * Constructor for the JWTAuthFilter class.
     * It initializes the JWT service.
     *
     * @param jwtService the JWT service
     */
    public JWTAuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * This method is used to filter requests.
     * It extracts the JWT token from the Authorization header, validates it, and sets the authentication in the security context.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
     * This method is used to extract authorities from JWT claims.
     * It splits the roles claim by comma and maps each role to a SimpleGrantedAuthority.
     *
     * @param claims the JWT claims
     * @return a list of granted authorities
     */
    private List<GrantedAuthority> extractAuthorities(Claims claims) {
        return Arrays.stream(claims.get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}