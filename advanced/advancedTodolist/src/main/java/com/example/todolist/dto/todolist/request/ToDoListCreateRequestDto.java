package com.example.todolist.dto.todolist.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToDoListCreateRequestDto {
    private String name;
    private String title;
    private String contents;
}
