package com.example.todolist.Exception;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException e) {
        return ResponseEntity.badRequest().body("잘못된 요청 : " + e.getMessage());
    }

    @ExceptionHandler(MismatchRequestValueException.class)
    public ResponseEntity<String> handleMismatchRequestValueException(MismatchRequestValueException e) {
        return ResponseEntity.badRequest().body("잘못된 접근 : " + e.getMessage());
    }
}
