package com.example.todolist.controller.todo;

import com.example.todolist.common.Const;
import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.request.ToDoListUpdateRequestDto;
import com.example.todolist.dto.todolist.response.*;
import com.example.todolist.common.session.LoginUserSession;
import com.example.todolist.service.todo.ToDoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<ToDoListCreateResponseDto> createToDoList(@RequestBody @Valid ToDoListCreateRequestDto requestDto, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(toDoService.saveToDo(requestDto,user.getUser().getId()), HttpStatus.CREATED);

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

    //투두리스트 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<ToDoListAllFindResponseDto>> findAllToDoList(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(toDoService.findAllTodo(pageable), HttpStatus.OK);
    }

    //투두리스트 나의 것만 조회
    @GetMapping("/users-todolist")
    public ResponseEntity<List<ToDoListMineFindResponseDto>> findMyToDoList(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(toDoService.findMyTodo(user.getUser().getId()), HttpStatus.OK);
    }

//    투두리스트 수정 > lv2 이름
    @PatchMapping("/{id}")
    public ResponseEntity<ToDoListUpdateResponseDto> updateTodoById(@PathVariable Long id, @RequestBody ToDoListUpdateRequestDto requestDto, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(toDoService.updateToDo(id, user.getUser().getId(), requestDto.getTitle(), requestDto.getContents()), HttpStatus.OK);
    }

    //투두리스트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoListDeleteResponseDto> deleteToDoList(@PathVariable Long id,HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(toDoService.deleteToDo(id,user.getUser().getId()),HttpStatus.OK);
    }



}