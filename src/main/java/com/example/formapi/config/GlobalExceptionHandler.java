package com.example.formapi.config;

import com.example.formapi.exception.CustomException;
import com.example.formapi.exception.ErrorDto;
import com.example.formapi.exception.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(InvalidEntityException ex) {
        ex.printStackTrace();
        String message = String.format("Invalid entity with id: %s", ex.getId());
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDto error = generateError(message, status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleCustomException(CustomException ex) {
        ex.printStackTrace();
        String message = ex.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDto error = generateError(message, status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        ex.printStackTrace();
        String message = "Something went wrong with your request";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDto error = generateError(message, status);
        return new ResponseEntity<>(error, status);
    }

    private ErrorDto generateError(String message, HttpStatus status) {
        return new ErrorDto(message, status);
    }
}