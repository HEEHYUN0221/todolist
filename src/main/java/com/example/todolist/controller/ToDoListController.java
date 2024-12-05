package com.example.todolist.controller;

import com.example.todolist.dto.ToDoListRequestDto;
import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    private final ToDoService toDoService;


    public ToDoListController(ToDoService toDoService){
        this.toDoService=toDoService;
    }

    // 투두리스트 생성
    @PostMapping
    public ResponseEntity<ToDoListResponseDto> createToDoList(@RequestBody ToDoListRequestDto requestDto) {
        return new ResponseEntity<>(toDoService.saveToDo(requestDto), HttpStatus.CREATED);
    }

    //투두리스트 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> findToDoList(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.findToDoById(id),HttpStatus.OK);
    }

    //투두리스트 전체 조회
    @GetMapping
    public ResponseEntity<List<ToDoListResponseDto>> findAllToDoList() {
        return new ResponseEntity<>(toDoService.findAllTodo(),HttpStatus.OK);
    }

    //투두리스트 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> updateTodoById(@PathVariable Long id, @RequestBody ToDoListRequestDto requestDto) {
        return new ResponseEntity<>(toDoService.updateToDo(id,requestDto.getUserId(),requestDto.getPassword(),requestDto.getName(), requestDto.getContents()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> deleteToDoList(@PathVariable Long id,@RequestBody ToDoListRequestDto requestDto) {
       toDoService.deleteToDo(id, requestDto.getUserId(),requestDto.getPassword());
       return new ResponseEntity<>(HttpStatus.OK);
    }


}