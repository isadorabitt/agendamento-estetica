package com.clinicaestetica.agendamento_estetica.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        // Converte a chave secreta em um objeto Key
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Assina o token com a chave
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey()) // Configura a chave de assinatura
                .build()
                .parseClaimsJws(token) // Faz o parsing do token
                .getBody();
        return claims.getSubject(); // Retorna o email (subject) do token
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey()) // Configura a chave de assinatura
                    .build()
                    .parseClaimsJws(token); // Faz o parsing do token
            return true; // Token válido
        } catch (Exception e) {
            return false; // Token inválido
        }
    }
}