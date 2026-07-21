package com.cognizant.springlearn.security;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cognizant.springlearn.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Validates a "Bearer &lt;jwt&gt;" Authorization header on every request (Doc 5 -
 * "Authorize based on JWT"). If the token is valid, the user is marked as
 * authenticated in the Spring Security context.
 *
 * Boot 3 modernization vs the doc: jakarta.servlet.* (not javax), and
 * jjwt 0.11 parsing via Jwts.parserBuilder().
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        LOGGER.info("START JwtAuthorizationFilter constructor");
        LOGGER.debug("Authentication manager: {}", authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("START doFilterInternal()");
        String header = req.getHeader(SecurityConstants.HEADER);
        LOGGER.debug("Authorization header: {}", header);

        // No Bearer token -> let the chain continue (e.g. Basic auth on /authenticate)
        if (header == null || !header.startsWith(SecurityConstants.BEARER)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(req, res);
        LOGGER.info("END doFilterInternal()");
    }

    /** Parse and verify the JWT; on success build an authenticated token. */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if (token != null) {
            try {
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(SecurityConstants.SECRET_KEY)
                        .build()
                        .parseClaimsJws(token.replace(SecurityConstants.BEARER, ""));
                String user = jws.getBody().getSubject();
                LOGGER.debug("Authenticated user from token: {}", user);
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            } catch (JwtException ex) {
                LOGGER.warn("Invalid JWT: {}", ex.getMessage());
                return null;
            }
            return null;
        }
        return null;
    }
}
