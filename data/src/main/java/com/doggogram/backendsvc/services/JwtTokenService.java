package com.doggogram.backendsvc.services;

public interface JwtTokenService {
    String generateToken(String user);
}
