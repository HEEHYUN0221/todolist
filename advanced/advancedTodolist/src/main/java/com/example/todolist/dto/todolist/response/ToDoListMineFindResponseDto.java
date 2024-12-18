package com.example.todolist.dto.todolist.response;

import com.example.todolist.entity.Todolist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToDoListMineFindResponseDto {
    Long id;
    String title;
    String contents;
    String userName;
    LocalDateTime date;
    LocalDateTime modify_date;

    public ToDoListMineFindResponseDto(Todolist todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userName = todo.getUser().getUserName();
        this.date = todo.getCreatedAt();
        this.modify_date = todo.getLastModifiedAt();
    }

    public static ToDoListMineFindResponseDto toDto(Todolist todo) {
        return new ToDoListMineFindResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getUser().getUserName(),
                todo.getCreatedAt(),
                todo.getLastModifiedAt()
        );
    }
}
