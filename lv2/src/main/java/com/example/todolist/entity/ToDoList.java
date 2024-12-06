package com.example.todolist.entity;

import com.example.todolist.dto.ToDoListRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class ToDoList {
    @Setter
    String name;
    @Setter
    Long id;//게시글 식별자
    @Setter
    String userId;//유저 식별자
    @Setter
    String title;
    @Setter
    String contents;
    LocalDate date;
    LocalDate modifyDate;
    String password;

    public ToDoList(String userId, String name,String title, String contents,String password) {
        this.name = name;
        this.contents = contents;
        this.title = title;
        this.userId = userId;
        this.password=password;
        date = LocalDate.now();
        modifyDate = LocalDate.now();
    }

}
