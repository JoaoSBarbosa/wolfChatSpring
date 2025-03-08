package com.barbosa.wolfChat.controllers.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.barbosa.wolfChat.core.services.exception.ServiceNotFoudEntityException;
import com.barbosa.wolfChat.utils.model.StandardError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionsHandler {

    @ExceptionHandler(ServiceNotFoudEntityException.class)
    public ResponseEntity<StandardError> entityNotFound(ServiceNotFoudEntityException ex, HttpServletRequest request) {
        StandardError standardError = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;
        standardError.setMessage(ex.getMessage());
        standardError.setError("Entidade não localizada! 😶‍🌫️");
        standardError.setPath(request.getRequestURI());
        standardError.setStatus(status.value());
        standardError.setTimestamp(Instant.now());

        return new ResponseEntity<>(standardError, status);
    }
}
