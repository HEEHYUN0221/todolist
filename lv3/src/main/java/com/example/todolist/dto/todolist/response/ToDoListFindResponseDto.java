package com.example.todolist.dto.todolist.response;

import com.example.todolist.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ToDoListFindResponseDto {
    Long id;
    String title;
    String contents;
    String name;
    LocalDate date;
    LocalDate modify_date;

    public ToDoListFindResponseDto(ToDoList todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.name = todo.getName();
        this.date = todo.getDate();
        this.modify_date=todo.getModifyDate();
    }

}
