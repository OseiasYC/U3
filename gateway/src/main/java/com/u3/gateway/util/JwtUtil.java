package com.u3.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {

    }

    public Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token.substring(7)).getBody();
    }

    private boolean isTokenExpired(String token) {

        return this.getAllClaimsFromToken(token).getExpiration().getTime() <= new Date().getTime();
    }

    public boolean isTokenValid(String token) {

        return !isTokenExpired(token);
    }

}
