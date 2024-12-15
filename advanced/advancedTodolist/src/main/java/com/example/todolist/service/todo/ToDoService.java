package com.example.todolist.service.todo;

import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ToDoService {
    ToDoListCreateResponseDto saveToDo(ToDoListCreateRequestDto requestDto);

    List<ToDoListFindResponseDto> findAllTodo(int pageNumber, int pageSize);

    List<ToDoListFindResponseDto> findMyTodo(Long userId);

    ToDoListFindResponseDto findToDoById(Long id);

    ToDoListFindResponseDto updateToDo(Long id, Long userId, String password, String name, String contents);

    void deleteToDo(Long id, Long userId, String password);

    List<ToDoListFindResponseDto> findToDoListNameUpdateDate(String name, LocalDate modifyDate);
}
