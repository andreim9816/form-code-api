package com.example.formapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidEntityException extends CustomException {
    private Long id;

    public InvalidEntityException(Long id) {
        super("Entity not found");
        this.id = id;
    }

    public InvalidEntityException(String message) {
        super(message);
    }
}
