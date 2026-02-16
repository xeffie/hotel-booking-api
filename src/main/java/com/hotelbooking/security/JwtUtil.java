package com.hotelbooking.security;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.jsonwebtoken.Claims;

@Component
public class JwtUtil {
    private static final String SECRET = "123";


    public String generateToken(String username, String role){
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
