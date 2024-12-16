package com.example.todolist.controller.todo;

import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.request.ToDoListUpdateRequestDto;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.service.todo.ToDoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    private final ToDoService toDoService;


    public ToDoListController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    //투두리스트 생성
    @PostMapping
    public ResponseEntity<ToDoListCreateResponseDto> createToDoList(@RequestBody @Valid ToDoListCreateRequestDto requestDto) {
        return new ResponseEntity<>(toDoService.saveToDo(requestDto), HttpStatus.CREATED);

    }


    //투두리스트 식별자 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ToDoListFindResponseDto> findToDoList(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.findToDoById(id), HttpStatus.OK);
    }

    //이름, 수정일 기준 조회
    @GetMapping
    public ResponseEntity<List<ToDoListFindResponseDto>> findToDoListNameUpdateDate(@RequestParam String name, @RequestParam LocalDate modifyDate) {
        return new ResponseEntity<>(toDoService.findToDoListNameUpdateDate(name, modifyDate), HttpStatus.OK);
    }

    //투두리스트 전체 조회 - Lv8에서 페이징 수정 예정.
    @GetMapping("/all")
    public ResponseEntity<List<ToDoListFindResponseDto>> findAllToDoList() {
        return new ResponseEntity<>(toDoService.findAllTodo(), HttpStatus.OK);
    }

    //투두리스트 나의 것만 조회 - Lv2 유저 테이블 생성 이후 수정 예정.
    @GetMapping("/users-todolist/{userId}")
    public ResponseEntity<List<ToDoListFindResponseDto>> findMyToDoList(@PathVariable Long userId) {
        return new ResponseEntity<>(toDoService.findMyTodo(userId), HttpStatus.OK);
    }

    //투두리스트 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ToDoListFindResponseDto> updateTodoById(@PathVariable Long id, @RequestBody ToDoListUpdateRequestDto requestDto) {
        return new ResponseEntity<>(toDoService.updateToDo(id, requestDto.getName(), requestDto.getContents()), HttpStatus.OK);
    }

    //투두리스트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoListCreateResponseDto> deleteToDoList(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}