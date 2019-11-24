package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.security.responses.JwtTokenResponse;

import javax.transaction.Transactional;

@Transactional
public interface AuthService {
    JwtTokenResponse generateJWTToken (String user, String password);
}
