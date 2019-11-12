package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.services.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value ("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String user) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
            .setClaims(new HashMap<>())
            .setSubject(user)
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 10000);
    }

    public String getUserFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
    }

    private Boolean isTokenNotExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    public Optional<Boolean> validateToken(String token) {
        return  isTokenNotExpired(token) ? Optional.of(Boolean.TRUE) : Optional.empty();
    }

}
