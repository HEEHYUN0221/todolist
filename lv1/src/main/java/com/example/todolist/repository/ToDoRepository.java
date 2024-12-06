package com.example.todolist.repository;

import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.entity.ToDoList;

import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository {

    ToDoListResponseDto saveToDo(ToDoList todo);

    List<ToDoListResponseDto> findAllToDo();

    ToDoList findToDoById(Long id);

    int updateTodo(Long id, String name, String contents, LocalDate modifyDate);

    void deleteToDo(Long id);
}
