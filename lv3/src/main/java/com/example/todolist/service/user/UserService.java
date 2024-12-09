package com.example.todolist.service.user;

import com.example.todolist.dto.user.request.UserCreateRequestDto;
import com.example.todolist.dto.user.request.UserUpdateRequestDto;
import com.example.todolist.dto.user.response.UserCreateResponseDto;
import com.example.todolist.dto.user.response.UserFindResponseDto;

public interface UserService {

    //생성
    UserCreateResponseDto createUser(UserCreateRequestDto requestDto);

    //유저 조회
    UserFindResponseDto findUser(Long userId);

    //유저 수정
    UserFindResponseDto updateUser(Long userId, UserUpdateRequestDto requestDto);

    //유저 삭제
    void deleteUser(Long userId);
}
