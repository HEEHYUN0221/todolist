package com.example.todolist.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<String> handleMissingPatchVariableException(MissingPathVariableException e) {
        return new ResponseEntity<>("url 파라미터 값 부족 : " + e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new ResponseEntity<>("요구 파라미터 부족 : " + e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException e) {
        return ResponseEntity.badRequest().body("잘못된 요청 : " + e.getMessage());
    }

    @ExceptionHandler(IdValueNotFoundException.class)
    public ResponseEntity<String> handleIdValueNotFoundException(IdValueNotFoundException e) {
        return new ResponseEntity<>("잘못된 값 : " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity<String> handleInvalidAccessException(InvalidAccessException e) {
        return new ResponseEntity<>("잘못된 접근 : " + e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataNotModifyException.class)
    public ResponseEntity<String> handleDataModifyException(DataNotModifyException e) {
        return new ResponseEntity<>("데이터가 수정되지 않음 : " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        return new ResponseEntity<>("데이터베이스 오류 : " + e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("알수없는 예외" + e.getMessage(), HttpStatus.NOT_FOUND);
    }


}
