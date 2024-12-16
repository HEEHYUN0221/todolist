package com.example.todolist.dto.todolist.response;

import com.example.todolist.entity.ToDoList;
import com.example.todolist.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToDoListCreateResponseDto {
    Long id;
    String title;
    String contents;
    String userName;
    LocalDateTime date;
    LocalDateTime modify_date;

    public ToDoListCreateResponseDto(ToDoList todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userName = todo.getUser().getUserName();
        this.date = todo.getCreatedAt();
        this.modify_date = todo.getLastModifiedAt();
    }


}
