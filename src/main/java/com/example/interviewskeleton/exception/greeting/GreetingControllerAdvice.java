package com.example.interviewskeleton.exception.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GreetingControllerAdvice {

    @ExceptionHandler(UnsupportedLocaleEx.class)
    public ResponseEntity<ApiErrorResponse> handleCompletionException(UnsupportedLocaleEx e) {
        log.warn("UnsupportedLocaleException occurred: {}", e.getMessage());
        return new ResponseEntity<>(ApiErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
