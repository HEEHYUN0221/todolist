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
    String email;
    LocalDateTime createdAt;

    public UserCreateResponseDto(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }

}
