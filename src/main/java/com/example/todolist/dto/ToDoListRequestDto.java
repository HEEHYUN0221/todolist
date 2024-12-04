package com.example.todolist.dto;

import com.example.todolist.entity.ToDoList;
import lombok.Getter;

@Getter
public class ToDoListRequestDto {
    private String title;
    private String contents;
    private String password;

    public ToDoListRequestDto(String title, String contents, String password) {
        this.title = title;
        this.contents = contents;
        this.password = password;
    }
}
