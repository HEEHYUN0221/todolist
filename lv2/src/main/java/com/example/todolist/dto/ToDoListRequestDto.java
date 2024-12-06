package com.example.todolist.dto;

import com.example.todolist.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToDoListRequestDto {
    private String userId;
    private String name;
    private String title;
    private String contents;
    private String password;

}
