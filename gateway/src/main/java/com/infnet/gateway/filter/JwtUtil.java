package com.infnet.gateway.filter;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public void validateToken(final String token) {
        SecretKey key = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
        Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
    }
}
