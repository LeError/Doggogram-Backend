package com.doggogram.backendsvc.services;

import java.util.Date;

public interface JwtTokenService {
    String generateToken(String user);
    Date calculateExpirationDate(Date createdDate);
}
