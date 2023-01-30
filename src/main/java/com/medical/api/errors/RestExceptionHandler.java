package com.medical.api.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityException(HttpServletRequest req,
                                                              SQLIntegrityConstraintViolationException ex) {
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(HttpServletRequest req,
                                                               NoSuchElementException ex) {
        String message = "No record found for given id in requested path: " + req.getRequestURI();
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, message));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }

    public static ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
