package com.example.todolist.Exception;

import org.springframework.http.HttpStatus;

public class InvalidAccessException extends RuntimeException {
    public InvalidAccessException(String message) {
        super(message);
    }
}
