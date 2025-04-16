package com.secureCrud.secureCrud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "yoursecretkeyyoursecretkeyyoursecretkey"; // 32+ characters for HS256

    // Generate the signing key for the JWT token
    private Key getSignKey() {
        System.out.println("⚡ Generating Signing Key");
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate a JWT token with username and role
    public String generateToken(String username, String role) {
        System.out.println("⚡ Generating Token for username: " + username + " with role: " + role);

        String token = Jwts.builder()
                .setSubject(username) // Subject is the username
                .claim("role", "ROLE_" + role) // Adding the role as a claim
                .setIssuedAt(new Date()) // Setting the issue time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration time
                .signWith(getSignKey(), SignatureAlgorithm.HS256) // Signing the token with HS256 algorithm
                .compact(); // Finalize and compact the token

        System.out.println("⚡ Generated Token: " + token);
        return token;
    }

    // Extract username from the token
    public String extractUsername(String token) {
        System.out.println("⚡ Extracting username from token");
        String username = extractClaim(token, Claims::getSubject);
        System.out.println("⚡ Extracted Username: " + username);
        return username;
    }

    // Extract the role from the token
    public String extractRole(String token) {
        System.out.println("⚡ Extracting role from token");
        String role = extractClaim(token, claims -> claims.get("role", String.class));
        System.out.println("⚡ Extracted Role: " + role);
        return role;
    }

    // Generic method to extract a claim (like username or role) from the JWT token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        System.out.println("⚡ Extracting claim from token");
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey()) // Setting the signing key
                .build()
                .parseClaimsJws(token) // Parsing the JWT token
                .getBody(); // Getting the body of the claims
        System.out.println("⚡ Extracted Claims: " + claims);
        return claimsResolver.apply(claims); // Applying the function to extract the claim
    }

    // Validate the token by checking if the username matches and token is not expired
    public boolean isTokenValid(String token, String username) {
        System.out.println("⚡ Validating Token");
        boolean isValid = extractUsername(token).equals(username) && !isTokenExpired(token);
        System.out.println("⚡ Token Validity: " + isValid);
        return isValid;
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        System.out.println("⚡ Checking if token is expired");
        boolean expired = extractClaim(token, Claims::getExpiration).before(new Date());
        System.out.println("⚡ Is Token Expired: " + expired);
        return expired;
    }
}
