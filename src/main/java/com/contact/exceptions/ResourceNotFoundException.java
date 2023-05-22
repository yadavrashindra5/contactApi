package com.contact.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@Builder
public class ResourceNotFoundException extends RuntimeException{
    private String message;
    private HttpStatus statusCode;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message, HttpStatus statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
