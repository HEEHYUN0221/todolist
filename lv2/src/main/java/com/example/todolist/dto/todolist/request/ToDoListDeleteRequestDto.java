package com.example.todolist.dto.todolist.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToDoListDeleteRequestDto {
    Long userId;
    String password;
}
