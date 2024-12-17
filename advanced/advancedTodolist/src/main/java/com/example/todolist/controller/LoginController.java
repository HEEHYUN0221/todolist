package com.example.todolist.controller;

import com.example.todolist.common.Const;
import com.example.todolist.dto.login.LoginRequestDto;
import com.example.todolist.dto.user.response.UserFindResponseDto;
import com.example.todolist.entity.User;
import com.example.todolist.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    //---쿠키때 사용하던 메소드---
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
    public String loginBySession(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        UserFindResponseDto responseDto = loginService.login(requestDto.getEmail(),requestDto.getPassword());
        Long userId = responseDto.getUserId();

        if(userId==null){
            throw new RuntimeException("유저 아이디가 없습니다.");
        }

        HttpSession session = request.getSession();

        session.setAttribute(Const.LOGIN_USER,responseDto);

        return "로그인 성공";
    }

    @PostMapping("/logout")
    public String logoutBySession(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "로그아웃 성공";
    }

}
