package com.example.todolist.repository.todo;

import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.entity.ToDoList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ToDoRepository {

    ToDoListCreateResponseDto saveToDo(ToDoList todo);

    List<ToDoListFindResponseDto> findAllToDo(int pageNumber, int pageSize);

    List<ToDoListFindResponseDto> findMyToDo(Long userId);

    ToDoList findToDoById(Long id);

    int updateTodo(Long id, Long userId, String name, String contents, LocalDateTime modifyDate);

    void deleteToDo(Long id);

    List<ToDoListFindResponseDto> findToDoListNameUpdateDate(String name, LocalDate modifyDate);
}
