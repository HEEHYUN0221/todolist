package com.example.todolist.controller;

import com.example.todolist.dto.ToDoListRequestDto;
import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.entity.ToDoList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    //DB연결 전까지 저장할 자료구조
    private final Map<Long, ToDoList> toDoListMap = new HashMap<>();

    // 투두리스트 생성
    public ResponseEntity<ToDoListResponseDto> createToDoList(@RequestBody ToDoListRequestDto requestDto) {
        // 식별자
        Long todoId = toDoListMap.isEmpty() ? 1 : Collections.max(toDoListMap.keySet()) + 1;

        // 요청받은 데이터로 객체 생성
        ToDoList todo = new ToDoList(todoId, requestDto.getTitle(), requestDto.getContents());

        //리스트에 저장
        toDoListMap.put(todoId, todo);

        return new ResponseEntity<>(new ToDoListResponseDto(todo), HttpStatus.CREATED);
    }

    //투두리스트 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> findToDoList(@PathVariable Long id) {

        ToDoList todo = toDoListMap.get(id);

        if(todo==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ToDoListResponseDto(todo), HttpStatus.OK);
    }

    //투두리스트 전체 조회
    @GetMapping
    public ResponseEntity<List<ToDoListResponseDto>> findAllToDoList() {
        List<ToDoListResponseDto> responseList = new ArrayList<>();

        for (ToDoList todo : toDoListMap.values()) {
            ToDoListResponseDto responseDto = new ToDoListResponseDto(todo);
            responseList.add(responseDto);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    //투두리스트 수정
    @PutMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> updateTodoById(@PathVariable Long id, @RequestBody ToDoListRequestDto requestDto) {


        ToDoList todo = toDoListMap.get(id);
        if(todo==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(todo.getTitle()==null||todo.getContents()==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (todo.passwordValidate(requestDto.getPassword())) {
            todo.update(requestDto);
        } else {
            //업데이트 실패 로그 출력, 클라이언트에게도 출력 해줘야함.
        }

        return new ResponseEntity<>(new ToDoListResponseDto(todo), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> deleteToDoList(@PathVariable Long id,@RequestBody String password) {
        //비밀번호 검증 로직 생각할 필요가 있는듯.
        if(toDoListMap.containsKey(id)||password.equals(toDoListMap.get(id).getPassword())) {
            toDoListMap.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}