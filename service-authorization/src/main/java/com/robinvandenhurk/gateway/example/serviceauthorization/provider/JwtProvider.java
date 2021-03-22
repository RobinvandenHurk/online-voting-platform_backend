package com.robinvandenhurk.gateway.example.serviceauthorization.provider;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Authority;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: JwtProvider
 */

public class JwtProvider {
    //    TODO: Find a way to take this out of the code
    private String SECRET_KEY = "secret";

    public <T> T extractClaim(String token, String claim, Class<T> type) {
        Claims claims = extractAllClaims(token);

        return claims.get(claim, type);
    }

    private Claims extractAllClaims(String token) throws SignatureException {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("authorities", user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList()));

        return createToken(claims, Integer.toString(user.getId()));
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 5))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token) {
        try {
            return (token != null && // Token can not be null
                    !token.isEmpty() && // Token can not be empty
                    !extractClaim(token, "exp", Date.class).before(new Date())); // Token can not be expired
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
            return false;
        }
    }

}
