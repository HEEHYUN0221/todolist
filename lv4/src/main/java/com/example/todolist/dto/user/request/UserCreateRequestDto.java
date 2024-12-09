package com.example.todolist.dto.user.request;


import lombok.Getter;


@Getter
public class UserCreateRequestDto {
    String userName;
    String email;

    public UserCreateRequestDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}





