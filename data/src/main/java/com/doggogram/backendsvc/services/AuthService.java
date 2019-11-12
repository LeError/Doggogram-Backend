package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.security.JwtTokenResponse;

public interface AuthService {
    JwtTokenResponse generateJWTToken (String user, String password);
}
