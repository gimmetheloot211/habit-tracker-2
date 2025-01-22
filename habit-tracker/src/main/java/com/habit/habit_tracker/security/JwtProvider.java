package com.habit.habit_tracker.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    private String secretKey;
    private long validityInMilliseconds;
    private Key signingKey;

    private final UserDetailsService userDetailsService;

    public JwtProvider(UserDetailsService userDetailsService,
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}") long validityInMilliseconds) {
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(Long userId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("JWT validation error: " + e.getMessage());
            return false;
        }
    }

    public Long getUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }

    public Authentication getAuthentication(String token) {
        Long userId = getUserId(token);

        if (userDetailsService instanceof UserServiceImpl) {
            UserDetails userDetails = ((UserServiceImpl) userDetailsService).loadUserById(userId);
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        }

        return null;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
