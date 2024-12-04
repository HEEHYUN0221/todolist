package com.example.todolist.service;

import com.example.todolist.dto.ToDoListRequestDto;
import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.entity.ToDoList;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import repository.ToDoRepository;

import java.util.List;

public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {this.toDoRepository=toDoRepository;}

    @Override
    public ToDoListResponseDto saveToDo(ToDoListRequestDto requestDto) {
        ToDoList todo = new ToDoList(requestDto.getTitle(), requestDto.getContents());
        ToDoList savedTodo = toDoRepository.saveToDo(todo);
        return new ToDoListResponseDto(savedTodo);
    }

    @Override
    public List<ToDoListResponseDto> findAllTodo() {
        List<ToDoListResponseDto> allToDo = toDoRepository.findAllToDo();
        return allToDo;
    }
 /*점심먹고 마저하기 */
    @Override
    public ToDoListResponseDto findToDoById(Long id) {
        ToDoList todo = toDoRepository.findToDoById(id);

        if(todo==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        return new ToDoListResponseDto(todo);
    }

    @Override
    public ToDoListResponseDto updateToDo(Long id, String password, String title, String contents) {
        ToDoList todo = toDoRepository.findToDoById(id);

        if(todo==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        if(todo.passwordValidate(password)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Password not match. ");
        }
        if(title==null||contents==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"title, content are required");
        }

        todo.update(title,contents);
    }

    @Override
    public void deleteToDo(Long id, String password) {

    }
}
