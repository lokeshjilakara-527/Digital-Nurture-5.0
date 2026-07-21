package com.cognizant.springlearn.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.security.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Authentication service (Doc 5 - "Create authentication service that returns JWT").
 *
 * Flow:  client sends Basic credentials  ->  Spring Security authenticates them
 *        -> this controller reads the Authorization header, extracts the user,
 *        generates a signed JWT and returns it as {"token": "..."}.
 *
 *   curl -s -u user:pwd http://localhost:8083/authenticate
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader(SecurityConstants.HEADER) String authHeader) {
        LOGGER.info("START authenticate()");
        LOGGER.debug("Authorization header: {}", authHeader);

        String user = getUser(authHeader);
        String token = generateJwt(user);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("END authenticate()");
        return map;
    }

    /** Decode the Base64 Basic credentials and return the username (text before ':'). */
    private String getUser(String authHeader) {
        LOGGER.info("START getUser()");
        String encodedCredentials = authHeader.replace(SecurityConstants.BASIC, "");
        byte[] decoded = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decoded, StandardCharsets.UTF_8);
        String user = credentials.substring(0, credentials.indexOf(':'));
        LOGGER.debug("User: {}", user);
        LOGGER.info("END getUser()");
        return user;
    }

    /** Build a signed JWT whose subject is the authenticated user. */
    private String generateJwt(String user) {
        LOGGER.info("START generateJwt()");
        Date now = new Date();
        String token = Jwts.builder()
                .setSubject(user)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + SecurityConstants.EXPIRATION_MS))
                .signWith(SecurityConstants.SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
        LOGGER.debug("Generated token: {}", token);
        LOGGER.info("END generateJwt()");
        return token;
    }
}
