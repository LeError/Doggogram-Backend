package com.doggogram.backendsvc.security;

import com.doggogram.backendsvc.services.JwtTokenService;
import com.doggogram.backendsvc.storage.exceptions.JwtAuthException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthProvider implements AuthenticationProvider {

    private final JwtTokenService jwtTokenService;

    @SuppressWarnings("unused")
    public JwtAuthProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthProvider(JwtTokenService jwtService) {
        this.jwtTokenService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            String token = (String) authentication.getCredentials();
            String username = jwtTokenService.getUserFromToken(token);

            return jwtTokenService.validateToken(token)
            .map(aBoolean -> new JwtAuthedProfile(username))
            .orElseThrow(() -> new JwtAuthException("JWT Token validation failed"));

        } catch (JwtException ex) {
            throw new JwtAuthException("Failed to verify token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuth.class.equals(authentication);
    }

}
