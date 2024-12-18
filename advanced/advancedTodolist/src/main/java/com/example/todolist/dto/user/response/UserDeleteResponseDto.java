package com.example.todolist.dto.user.response;

import com.example.todolist.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserDeleteResponseDto {
    public static final String DELETE_SUCCESS = "삭제 성공";
    String userName;
    String email;

    public UserDeleteResponseDto(User user) {
        this.email = user.getEmail();
        this.userName = user.getUserName();
    }

    public String getDeleteSuccess() {
        return userName + "의 " + "계정 " + email + DELETE_SUCCESS;
    }
}
