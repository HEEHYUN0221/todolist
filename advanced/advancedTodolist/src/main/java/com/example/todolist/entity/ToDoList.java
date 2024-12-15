package com.example.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ToDoList {
    String name;
    Long id;//게시글 식별자
    Long userId;//유저 식별자
    String title;
    String contents;
    LocalDateTime date;
    LocalDateTime modifyDate;
    String password;

    public ToDoList(Long userId, String password, String name, String title, String contents) {
        this.userId = userId;
        this.name = name;
        this.contents = contents;
        this.title = title;
        this.password = password;
        date = LocalDateTime.now();
        modifyDate = LocalDateTime.now();
    }

}
