package com.example.todolist.service;

import com.example.todolist.dto.ToDoListRequestDto;
import com.example.todolist.dto.ToDoListResponseDto;

import java.util.List;

public interface ToDoService {
    ToDoListResponseDto saveToDo(ToDoListRequestDto requestDto);

    List<ToDoListResponseDto> findAllTodo();

    ToDoListResponseDto findToDoById(Long id);

    ToDoListResponseDto updateToDo(Long id,String userId, String password, String name,String contents);

    void deleteToDo(Long id,String userId, String password);
}
