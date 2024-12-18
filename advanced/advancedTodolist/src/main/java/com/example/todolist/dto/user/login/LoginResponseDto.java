package com.example.todolist.dto.user.login;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    public static final String LOGIN_SUCCESS = "로그인 성공";

    public String getLoginSuccess() {
        return LOGIN_SUCCESS;
    }
}
