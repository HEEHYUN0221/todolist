package com.example.todolist.dto.todolist.response;

import com.example.todolist.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToDoListAllFindResponseDto {
    Long id;
    String title;
    String contents;
    String userName;
    LocalDateTime date;
    LocalDateTime modify_date;

    public ToDoListAllFindResponseDto(ToDoList todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userName = todo.getUser().getUserName();
        this.date = todo.getCreatedAt();
        this.modify_date = todo.getLastModifiedAt();
    }

    public static ToDoListAllFindResponseDto toDto(ToDoList todo) {
        return new ToDoListAllFindResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getUser().getUserName(),
                todo.getCreatedAt(),
                todo.getLastModifiedAt()
        );
    }
}
