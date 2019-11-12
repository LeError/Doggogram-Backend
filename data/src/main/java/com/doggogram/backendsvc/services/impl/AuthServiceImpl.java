package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.security.JwtTokenResponse;
import com.doggogram.backendsvc.services.AuthService;
import com.doggogram.backendsvc.services.JwtTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private JwtTokenService jwtTokenService;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl (UserRepository userRepository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtTokenResponse generateJWTToken (String user, String pass) {
        return userRepository.findById(user)
        .filter(userEntity -> passwordEncoder.matches(pass, userEntity.getPass()))
        .map(account -> new JwtTokenResponse(jwtTokenService.generateToken(user)))
        .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

}