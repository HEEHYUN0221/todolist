package com.example.todolist.controller;

import com.example.todolist.common.Const;
import com.example.todolist.dto.user.login.LoginRequestDto;
import com.example.todolist.dto.user.login.LoginResponseDto;
import com.example.todolist.dto.user.login.LoginUserSession;
import com.example.todolist.dto.user.login.LogoutResponseDto;
import com.example.todolist.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

//    ---쿠키때 사용하던 메소드---
//    @PostMapping("/login")
//    public String loginByCookie(@Valid @RequestBody LoginRequestDto requestDto) {
//        UserFindResponseDto loginUser = loginService.login(requestDto.getEmail(),requestDto.getPassword());
//        if(loginUser==null){
//            return "로그인 실패";
//        }
//
//        Cookie idCookie = new Cookie("userId",String.valueOf(loginUser.getUserId()));
//        response.addCookie(idCookie);
//        return "로그인 성공";
//
//    }
//    @PostMapping("/logout")
//    public String logoutByCookie(HttpServletResponse response) {
//        Cookie cookie = new Cookie("userId",null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//        return "로그아웃 성공";
//    }

    @PostMapping("/login")
    public LoginResponseDto loginBySession(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        LoginUserSession loginUser = loginService.login(requestDto.getEmail(),requestDto.getPassword());
        Long userId = loginUser.getUser().getId();

        if(userId==null){
            throw new RuntimeException("유저 아이디가 없습니다.");
        }

        HttpSession session = request.getSession();

        session.setAttribute(Const.LOGIN_USER,loginUser);

        return new LoginResponseDto();
    }

    @PostMapping("/logout")
    public LogoutResponseDto logoutBySession(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return new LogoutResponseDto();
    }

}
