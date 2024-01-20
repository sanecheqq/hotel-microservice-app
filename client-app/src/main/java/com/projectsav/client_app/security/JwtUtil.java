package com.projectsav.client_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${token.expire-time}")
    private Long accessTokenExpirationMs;

    private final Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateAccessToken(String username) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + accessTokenExpirationMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean checkToken(String token) {
        try {
            JwtParser jwtParser = Jwts
                    .parserBuilder()
                    .setSigningKey(signingKey)
                    .build();
            Claims claims = jwtParser
                    .parseClaimsJws(token)
                    .getBody();
            long expirationTime = claims
                    .getExpiration()
                    .getTime();
            long currentTime = System.currentTimeMillis();
            return currentTime <= expirationTime;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
