package com.example.todolist.dto.user.response;

import com.example.todolist.entity.User;
import lombok.Getter;


@Getter
public class UserUpdateResponseDto {

    public static final String UPDATE_SUCCESS = "업데이트 성공";
    String userName;
    String email;

    public UserUpdateResponseDto(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }

    public String getUpdateSuccess() {
        return UPDATE_SUCCESS;
    }
}
