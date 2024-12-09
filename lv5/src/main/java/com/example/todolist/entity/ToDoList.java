package com.example.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ToDoList {
    String name;
    Long id;//게시글 식별자
    Long userId;//유저 식별자
    String title;
    String contents;
    LocalDate date;
    LocalDate modifyDate;
    String password;

    public ToDoList(Long userId,String password,String name, String title, String contents) {
        this.userId=userId;
        this.name = name;
        this.contents = contents;
        this.title = title;
        this.password=password;
        date = LocalDate.now();
        modifyDate = LocalDate.now();
    }

}
