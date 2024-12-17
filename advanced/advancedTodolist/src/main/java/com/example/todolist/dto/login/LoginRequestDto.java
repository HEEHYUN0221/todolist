package com.example.todolist.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @Email
    String email;

    @NotBlank
    String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
