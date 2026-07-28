package com.example.bakimyonetim.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    // Gizli anahtarı application.properties'ten güvenli bir şekilde çekiyoruz
    @Value("${jwt.secret:bakimYonetimSistemiCokGizliBirAnahtarBuOlmaliEnAz32Karakter}")
    private String secretKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Token üretme
    public String generateToken(String email, String rol) {
        return Jwts.builder()
                .subject(email)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 saat geçerli
                .signWith(getSigningKey()) // Modern JJWT standardı
                .compact();
    }

    // Token'dan email çıkarma
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Token'dan rol çıkarma
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("rol", String.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Token geçerli mi kontrolü
    public boolean isTokenValid(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return extractedEmail.equals(email) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}