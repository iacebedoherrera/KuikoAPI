package com.kuiko.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommunityNotFoundException.class)
    public ResponseEntity<String> handleCommunityNotFoundException(CommunityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProvinceParamsNotValidException.class)
    public ResponseEntity<String> handleProvinceParamsNotValidException(ProvinceParamsNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CommunityParamsNotValidException.class)
    public ResponseEntity<String> handleCommunityParamsNotValidException(CommunityParamsNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
