package com.example.todolist.service.todo;

import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.*;

import java.time.LocalDate;
import java.util.List;

public interface ToDoService {
    ToDoListCreateResponseDto saveToDo(ToDoListCreateRequestDto requestDto, Long userId);

    List<ToDoListAllFindResponseDto> findAllTodo();

    List<ToDoListMineFindResponseDto> findMyTodo(Long userId);

    ToDoListFindResponseDto findToDoById(Long id);

    ToDoListUpdateResponseDto updateToDo(Long id, Long userId, String name, String contents);

    ToDoListDeleteResponseDto deleteToDo(Long id, Long userId);

    List<ToDoListFindResponseDto> findToDoListNameUpdateDate(String name, LocalDate modifyDate);
}
