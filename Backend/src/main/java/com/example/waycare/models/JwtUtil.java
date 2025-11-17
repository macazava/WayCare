package com.example.waycare.models;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtil {


    private final String secret = "segredoSuperSecreto123";


    private final long expiration = 86400000;


    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token não suportado");
        } catch (MalformedJwtException e) {
            System.out.println("Token malformado");
        } catch (IllegalArgumentException e) {
            System.out.println("Token vazio ou inválido");
        }
        return false;
    }
}
