package com.example.todolist.repository.user;

import com.example.todolist.dto.user.response.UserCreateResponseDto;
import com.example.todolist.dto.user.response.UserFindResponseDto;
import com.example.todolist.entity.User;

public interface UserRepository {

    //유저 생성 Create
    UserCreateResponseDto createUser(User user);

    //유저 조회 Read
    UserFindResponseDto findUser(Long userId);

    //유저 수정 Update
    int updateUser(Long userId, String name, String email);

    //유저 삭제 Delete
    void deleteUser(Long userId);
}
