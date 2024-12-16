package com.example.todolist.dto.user.response;


import com.example.todolist.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserCreateResponseDto {
    Long userId;
    String userName;
    String email;
    LocalDateTime registDate;

    public UserCreateResponseDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.registDate = user.getRegistDate();
    }

}