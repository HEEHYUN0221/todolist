package com.example.todolist.dto;

import com.example.todolist.entity.ToDoList;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ToDoListResponseDto {
    Long id;
    String title;
    String contents;
    LocalDate date;

    public ToDoListResponseDto(ToDoList todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.date = todo.getDate();
    }
}
