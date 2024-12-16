package com.example.todolist.dto.todolist.response;

import com.example.todolist.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToDoListCreateResponseDto {
    Long id;
    String title;
    String contents;
    String name;
    LocalDateTime date;
    LocalDateTime modify_date;

    public ToDoListCreateResponseDto(ToDoList todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.name = todo.getName();
        this.date = todo.getCreatedAt();
        this.modify_date = todo.getLastModifiedAt();
    }


}
