package com.example.todolist.dto.todolist.response;

import lombok.Getter;


public class ToDoListDeleteResponseDto {
    public static final String DELETE_SUCCESS = "삭제 성공";
    String title;

    public ToDoListDeleteResponseDto(String title) {
        this.title = title;
    }

    public String getDeleteSuccess() {
        return DELETE_SUCCESS;
    }

}
