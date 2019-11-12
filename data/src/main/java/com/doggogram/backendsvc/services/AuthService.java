package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.security.responses.JwtTokenResponse;

public interface AuthService {
    JwtTokenResponse generateJWTToken (String user, String password);
}
