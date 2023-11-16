package com.example.interviewskeleton.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.CompletionException;

@RestControllerAdvice
@Slf4j
public class FirstProblemControllerAdvice {

    @ExceptionHandler(CompletionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleCompletionException(CompletionException e) {
        log.warn("CompletionException occurred: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Bad req:" + e.getMessage());
    }
}
