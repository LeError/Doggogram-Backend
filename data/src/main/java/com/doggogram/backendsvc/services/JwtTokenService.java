package com.doggogram.backendsvc.services;

import io.jsonwebtoken.Claims;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Transactional
public interface JwtTokenService {
    String generateToken(String user);
    String getUserFromToken(String token);
    Date getExpirationDateFromToken(String token);
    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
    Optional<Boolean> validateToken(String token);
}
