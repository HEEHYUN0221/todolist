package com.example.todolist.dto.todolist.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToDoListUpdateRequestDto {
    private Long userId;
    private String password;
    private String name;
    private String contents;

}
