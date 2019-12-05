package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.services.AuthService;
import com.doggogram.backendsvc.util.exceptions.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping ( value = {"/api/v1/auth/login", "/api/v1/auth/login/"})
    public ResponseEntity createCustomer(@RequestParam("user") String user, @RequestParam("password") String pass) throws AuthException {
        try {
            return new ResponseEntity<>(authService.generateJWTToken(user, pass), HttpStatus.OK);
        } catch(Exception e) {
            throw new AuthException("Could not Authenticate!");
        }
    }

}
