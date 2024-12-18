package com.example.todolist.dto.user.response;

import com.example.todolist.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserFindResponseDto {
    String userName;
    String email;
    LocalDateTime modifyDate;

    public UserFindResponseDto(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.modifyDate=user.getLastModifyToDoList();
    }
}

