package com.cognizant.springlearn.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

/**
 * Shared JWT settings so the token generator (AuthenticationController) and the
 * token validator (JwtAuthorizationFilter) always use the same signing key.
 *
 * NOTE (Boot 3 modernization): the hands-on doc uses the literal secret
 * "secretkey" with jjwt 0.9.0. jjwt 0.11+ enforces the HS256 spec, which
 * requires a key of at least 256 bits (32 bytes), so we use a longer secret.
 */
public final class SecurityConstants {

    public static final String SECRET =
            "spring-learn-jwt-secret-key-0123456789-abcdefghij";  // >= 32 bytes

    public static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static final long EXPIRATION_MS = 20 * 60 * 1000L; // 20 minutes

    public static final String HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String BASIC = "Basic ";

    private SecurityConstants() {
    }
}
