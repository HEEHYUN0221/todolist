package com.example.todolist.dto;

import com.example.todolist.entity.ToDoList;
import lombok.Getter;


public class ToDoListRequestDto {
    @Getter
    private String title;
    @Getter
    private String contents;
    private String password;

    public boolean passwordValidate(ToDoListRequestDto requestDto) {
        return requestDto.password.equals(this.password);

    }


}
