package com.example.todolist.entity;

import com.example.todolist.dto.ToDoListRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
public class ToDoList {
    @Setter
    Long id;
    @Setter
    String title;
    @Setter
    String contents;
    LocalDate date;
    String password;

    public void update(String title, String contents) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        date = LocalDate.now();
    }

    public ToDoList(String title, String contents) {
        this.contents = contents;
        this.title = title;
        date = LocalDate.now();
    }

    public boolean passwordValidate(String password) {
        return password.equals(this.password);
    }
}
