package com.example.todolist.dto.todolist.response;

import com.example.todolist.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToDoListFindResponseDto {
    Long id;
    String title;
    String contents;
    Long userId;
    LocalDateTime date;
    LocalDateTime modify_date;

    public ToDoListFindResponseDto(ToDoList todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userId = todo.getUser().getId();
        this.date = todo.getCreatedAt();
        this.modify_date = todo.getLastModifiedAt();
    }

    public static ToDoListFindResponseDto toDto(ToDoList todo) {
        return new ToDoListFindResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getUser().getId(),
                todo.getCreatedAt(),
                todo.getLastModifiedAt()
        );
    }
}
