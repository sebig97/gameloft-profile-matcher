package com.example.playerprofileservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {

    private String message;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
