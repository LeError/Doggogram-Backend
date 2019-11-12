package com.doggogram.backendsvc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthedProfile implements Authentication {

    private final String username;

    public JwtAuthedProfile (String username) {
        this.username = username;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities () {
        return new ArrayList<>();
    }

    @Override public Object getCredentials () {
        return null;
    }

    @Override public Object getDetails () {
        return null;
    }

    @Override public Object getPrincipal () {
        return username;
    }

    @Override public boolean isAuthenticated () {
        return true;
    }

    @Override public void setAuthenticated (boolean isAuthenticated)
    throws IllegalArgumentException {

    }

    @Override public String getName () {
        return username;
    }
}