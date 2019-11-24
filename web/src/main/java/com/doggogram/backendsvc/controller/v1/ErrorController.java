package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.util.ErrorResponse;
import com.doggogram.backendsvc.util.exceptions.EntityCorruptedException;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(NOT_FOUND, details), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityCorruptedException.class)
    public final ResponseEntity<ErrorResponse> handleEntityCorruptedException(EntityCorruptedException e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(GONE, details), HttpStatus.GONE);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleUnhandledExceptions(Exception e, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        System.err.println(e.getMessage());
        details.add(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(SERVER_ERROR, details), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
