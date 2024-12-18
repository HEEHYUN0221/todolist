package com.example.todolist.dto.user.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class UserCreateRequestDto {
    @NotBlank(message = "유저 이름은 필수입니다.")
    String userName;

    @Email
    String email;

    @NotBlank
    String password;

    public UserCreateRequestDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password=password;
    }
}





