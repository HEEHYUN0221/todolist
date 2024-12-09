package com.example.todolist.Exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException e) {
        return ResponseEntity.badRequest().body("잘못된 요청 : " + e.getMessage());
    }

    @ExceptionHandler(IdValueNotFoundException.class)
    public ResponseEntity<String> handleIdValueNotFoundException(IdValueNotFoundException e){
        return new ResponseEntity<>("잘못된 값 : " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity<String> handleInvalidAccessException(InvalidAccessException e) {
        return new ResponseEntity<>("잘못된 접근 : " + e.getMessage(),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        return new ResponseEntity<>("데이터베이스 오류 : " + e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
    }


}
