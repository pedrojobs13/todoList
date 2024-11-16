package com.fattocs.tarefa.presentation.exception;

import com.fattocs.tarefa.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessException(BusinessException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
