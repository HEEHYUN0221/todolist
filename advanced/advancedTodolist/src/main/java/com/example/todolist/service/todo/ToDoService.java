package com.example.todolist.service.todo;

import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ToDoService {
    ToDoListCreateResponseDto saveToDo(ToDoListCreateRequestDto requestDto);

    List<ToDoListFindResponseDto> findAllTodo();

    List<ToDoListFindResponseDto> findMyTodo(Long userId);

    ToDoListFindResponseDto findToDoById(Long id);

    ToDoListFindResponseDto updateToDo(Long id, String name, String contents);

    void deleteToDo(Long id);

    List<ToDoListFindResponseDto> findToDoListNameUpdateDate(String name, LocalDate modifyDate);
}
