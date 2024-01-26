package com.TOSAN.onlineBookStore.security.jwt;


import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtil {
    private static final String SECRET = "elahe";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public static  boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
    public static Boolean validateToken(String token, UserDetails customer) {
        final String username = extractUsername(token);
        return (username.equals(customer.getUsername()) );
//                && !isTokenExpired(token));
    }
}