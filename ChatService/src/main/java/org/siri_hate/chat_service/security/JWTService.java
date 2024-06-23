package org.siri_hate.chat_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * This class represents a service for JWT (JSON Web Token) operations.
 * It contains methods to extract claims from a JWT, validate a JWT, and extract the username and expiration date from a JWT.
 */
@Service
public class JWTService {

    /**
     * The secret key for JWT signing and verification.
     * It is retrieved from the environment variable "JWT_SECRET".
     */
    public static final String SECRET = System.getenv("JWT_SECRET");

    /**
     * This method is used to get the signing key for JWT.
     * It decodes the secret key from Base64 and returns a HMAC SHA key for it.
     *
     * @return the signing key for JWT
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * This method is used to extract the username (subject claim) from a JWT.
     *
     * @param token the JWT
     * @return the username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * This method is used to extract the expiration date (expiration claim) from a JWT.
     *
     * @param token the JWT
     * @return the expiration date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * This method is used to extract a specific claim from a JWT.
     * It uses a function to resolve the claim from the JWT claims.
     *
     * @param token the JWT
     * @param claimsResolver the function to resolve the claim
     * @return the claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * This method is used to extract all claims from a JWT.
     * It parses the JWT and returns its claims.
     *
     * @param token the JWT
     * @return the claims
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
     * This method is used to check if a JWT is expired.
     * It extracts the expiration date from the JWT and checks if it is before the current date.
     *
     * @param token the JWT
     * @return true if the JWT is expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * This method is used to validate a JWT.
     * It checks if the JWT is expired.
     *
     * @param token the JWT
     * @return true if the JWT is valid, false otherwise
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}