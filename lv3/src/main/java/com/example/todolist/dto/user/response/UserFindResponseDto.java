package com.example.todolist.dto.user.response;

import com.example.todolist.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserFindResponseDto {
    Long userId;
    String userName;
    String email;
    LocalDate registDate;
    LocalDate lastModifyDate;

    public UserFindResponseDto(User user) {
        this.userId=user.getUserId();
        this.userName=user.getUserName();
        this.email=user.getEmail();
        this.registDate=user.getRegistDate();
        this.lastModifyDate=user.getLastModifyDate();
    }
}

