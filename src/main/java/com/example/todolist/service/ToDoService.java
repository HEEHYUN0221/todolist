package com.example.todolist.service;

import com.example.todolist.dto.ToDoListRequestDto;
import com.example.todolist.dto.ToDoListResponseDto;

import java.util.List;

public interface ToDoService {
    ToDoListResponseDto saveToDo(ToDoListRequestDto requestDto);

    List<ToDoListResponseDto> findAllTodo();

    ToDoListResponseDto findToDoById(Long id);

    ToDoListResponseDto updateToDo(Long id,String password, String title, String contents);

    void deleteToDo(Long id, String password);
}
