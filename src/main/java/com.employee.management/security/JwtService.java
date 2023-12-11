package com.employee.management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Getter
@Service
public class JwtService {
    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token){
        return  extractClaim(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
    String username= extractUsername(token);
    return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).after(new Date());
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private Key getSigningKey(){
       byte[] bytes= Decoders.BASE64.decode(getSecretKey());
       return Keys.hmacShaKeyFor(bytes);
   }
}
