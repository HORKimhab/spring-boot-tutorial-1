package com.hkimhab.basic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hkimhab.basic.response.ApiResponseCustomize;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound(NoSuchElementException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseCustomize<>(404, e.getMessage(), null));
    }
}
