package com.OficinaDeSoftware.EmissorCertificadosBackend.controller.exception;

import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.InternalServerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not founded", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<StandardError> internalServerError( InternalServerError e, HttpServletRequest request ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Internal server error", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
