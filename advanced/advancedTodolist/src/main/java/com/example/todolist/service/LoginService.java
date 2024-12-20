package com.example.todolist.service;

import com.example.todolist.Exception.IdValueNotFoundException;
import com.example.todolist.Exception.InvalidAccessException;
import com.example.todolist.config.PasswordEncoder;
import com.example.todolist.common.session.LoginUserSession;
import com.example.todolist.entity.User;
import com.example.todolist.repository.user.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public LoginUserSession login(@Email String email, @NotBlank String password) {

        User user = userRepository.findUserByEmail(email);

        if(user==null) {
            throw new IdValueNotFoundException("없는 이메일 입니다.");
        }

        if(!PasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidAccessException("비밀번호가 일치하지 않습니다.");
        }

        return new LoginUserSession(user);

    }
}
