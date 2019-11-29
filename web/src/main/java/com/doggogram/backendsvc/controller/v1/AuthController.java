package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.services.AuthService;
import com.doggogram.backendsvc.util.exceptions.AuthException;
import com.doggogram.backendsvc.util.requests.AuthRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping ( value = {"/api/v1/auth/login", "/api/v1/auth/login/"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@RequestBody AuthRequest authRequest) throws AuthException {
        try {
            return new ResponseEntity<>(authService.generateJWTToken(authRequest.getUser(), authRequest.getPass()), HttpStatus.OK);
        } catch(Exception e) {
            throw new AuthException("Could not Authenticate!");
        }
    }

}
