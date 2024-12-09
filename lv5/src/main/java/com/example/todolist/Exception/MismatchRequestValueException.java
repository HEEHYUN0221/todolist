package com.example.todolist.Exception;

public class MismatchRequestValueException extends RuntimeException {
    public MismatchRequestValueException(String message) {
        super(message);
    }
}
