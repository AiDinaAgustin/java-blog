package com.fastcampus.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException exception) {
        List<String> errorMessages = new ArrayList<>(Collections.singletonList(exception.getMessage()));
        ApiExceptionResponse response = ApiExceptionResponse.builder()
                .ErrorMessage(errorMessages)
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handler(MethodArgumentNotValidException exception) {
        List<String> errorMessages = new ArrayList<>();
        exception.getFieldErrors().forEach(fieldError -> errorMessages.add(
                fieldError.getField() + " " + fieldError.getDefaultMessage()
        ));
        ApiExceptionResponse response = ApiExceptionResponse.builder()
                .ErrorMessage(errorMessages)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
