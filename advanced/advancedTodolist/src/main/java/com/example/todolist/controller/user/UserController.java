package com.example.todolist.controller.user;

import com.example.todolist.common.Const;
import com.example.todolist.common.session.LoginUserSession;
import com.example.todolist.dto.user.request.UserCreateRequestDto;
import com.example.todolist.dto.user.request.UserUpdateRequestDto;
import com.example.todolist.dto.user.response.*;
import com.example.todolist.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //유저 생성
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto requestDto) {
        return new ResponseEntity<>(userService.createUser(requestDto), HttpStatus.CREATED);
    }

    //유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserFindResponseDto> findUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findUser(userId), HttpStatus.OK);
    }

    //유저 수정(이름, 이메일)
    @PatchMapping("/my-info/modify")
    public ResponseEntity<UserUpdateResponseDto> updateUser(@RequestBody UserUpdateRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(userService.updateUser(user.getUser().getId(), requestDto), HttpStatus.OK);
    }


    //유저 삭제
    @DeleteMapping("/my-info/delete")
    public ResponseEntity<UserDeleteResponseDto> deleteUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        UserDeleteResponseDto deleteUser = userService.deleteUser(user.getUser().getId());

        session.invalidate();

        return new ResponseEntity<>(deleteUser,HttpStatus.OK);
    }

    @DeleteMapping("/my-info/withdrawal")
    public ResponseEntity<UserWithdrawalResponseDto> withdrawalUser(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(userService.withdrawalUser(user.getUser().getId()),HttpStatus.OK);
    }


}
