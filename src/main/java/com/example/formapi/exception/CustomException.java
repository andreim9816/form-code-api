package com.example.formapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private String message;

    public CustomException(String message) {
        this.message = message;
    }
}
