package com.example.todolist.entity;

import com.example.todolist.dto.ToDoListRequestDto;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class ToDoList {
    Long id;
    String title;
    String contents;
    LocalDate date;

    public void update(ToDoListRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        date = LocalDate.now();
    }

    public ToDoList(Long id, String contents, String title) {
        this.contents = contents;
        this.title = title;
        this.id = id;
        date = LocalDate.now();
    }
}
