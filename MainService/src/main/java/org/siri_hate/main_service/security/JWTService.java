package org.siri_hate.main_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * This class represents a service for handling JSON Web Tokens (JWT).
 * It provides methods for extracting information from a token, such as the username and expiration date.
 * It also provides a method for validating a token by checking if it is expired.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class JWTService {

    public static final String SECRET = System.getenv("JWT_SECRET");

    /**
     * This method returns the signing key for the JWT, which is derived from a secret environment variable.
     *
     * @return a Key that can be used to sign a JWT.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * This method extracts the username from a JWT.
     *
     * @param token the JWT from which to extract the username.
     * @return the username from the JWT.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * This method extracts the expiration date from a JWT.
     *
     * @param token the JWT from which to extract the expiration date.
     * @return the expiration date from the JWT.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * This method extracts a claim from a JWT using a provided claims resolver function.
     *
     * @param token          the JWT from which to extract the claim.
     * @param claimsResolver the function to use to extract the claim.
     * @return the claim from the JWT.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * This method extracts all claims from a JWT.
     *
     * @param token the JWT from which to extract the claims.
     * @return the claims from the JWT.
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * This method checks if a JWT is expired.
     *
     * @param token the JWT to check.
     * @return true if the JWT is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * This method validates a JWT by checking if it is expired.
     *
     * @param token the JWT to validate.
     * @return true if the JWT is not expired, false otherwise.
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}