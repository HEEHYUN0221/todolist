package com.example.todolist.repository.todo;

import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.entity.ToDoList;

import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository {

    ToDoListCreateResponseDto saveToDo(ToDoList todo);

    List<ToDoListFindResponseDto> findAllToDo();

    List<ToDoListFindResponseDto> findMyToDo(Long userId);

    ToDoList findToDoById(Long id);

    int updateTodo(Long id, String name, String contents, LocalDate modifyDate);

    void deleteToDo(Long id);
}
