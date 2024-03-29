package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.util.exceptions.AuthException;
import com.doggogram.backendsvc.util.exceptions.ControllerCountException;
import com.doggogram.backendsvc.util.exceptions.ImageUploadException;
import com.doggogram.backendsvc.util.exceptions.UserRegistrationException;
import com.doggogram.backendsvc.util.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private final String SERVER_ERROR = "Internal Server Error!";
    private final String NOT_FOUND = "Requested Resource not Found!";
    private final String GONE = "Requested Resource was corrupted!";
    private final String BAD_REQUEST ="Bad  Request!";
    private final String UNAUTHORIZED ="Unauthorized!";
    private final String SERVICE_UNAVAILABLE ="Service not available!";

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        details.add(webRequest.getDescription(false));
        return new ResponseEntity<>(new ErrorResponse("EntityNotFoundException", NOT_FOUND, details), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRegistrationException.class)
    public final ResponseEntity<ErrorResponse> handleUserRegistrationException(UserRegistrationException e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        details.add(webRequest.getDescription(false));
        return new ResponseEntity<>(new ErrorResponse("UserRegistrationException", BAD_REQUEST, details), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public final ResponseEntity<ErrorResponse> handleAuthException(AuthException e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        details.add(webRequest.getDescription(false));
        return new ResponseEntity<>(new ErrorResponse("AuthException", UNAUTHORIZED, details), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ControllerCountException.class)
    public final ResponseEntity<ErrorResponse> handleControllerCountException(ControllerCountException e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        details.add(webRequest.getDescription(false));
        return new ResponseEntity<>(new ErrorResponse("ControllerCountException", SERVICE_UNAVAILABLE, details), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ImageUploadException.class)
    public final ResponseEntity<ErrorResponse> handleImageUploadException(ImageUploadException e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        details.add(webRequest.getDescription(false));
        return new ResponseEntity<>(new ErrorResponse("ImageUploadException", BAD_REQUEST, details), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleUnhandledExceptions(Exception e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        details.add(webRequest.getDescription(false));
        details.add("A unhandled Exception was thrown! Please Report to the Administrator / Support!");
        return new ResponseEntity<>(new ErrorResponse("Exception", SERVER_ERROR, details), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
