package com.example.todolist.service.user;

import com.example.todolist.config.PasswordEncoder;
import com.example.todolist.dto.user.request.UserCreateRequestDto;
import com.example.todolist.dto.user.request.UserUpdateRequestDto;
import com.example.todolist.dto.user.response.UserCreateResponseDto;
import com.example.todolist.dto.user.response.UserDeleteResponseDto;
import com.example.todolist.dto.user.response.UserFindResponseDto;
import com.example.todolist.dto.user.response.UserUpdateResponseDto;
import com.example.todolist.entity.User;
import com.example.todolist.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override//유저 생성 Create
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {

        String encodedPassword = PasswordEncoder.encode(requestDto.getPassword());

        User saveUser = new User(
                requestDto.getUserName(),
                requestDto.getEmail(),
                encodedPassword
        );
        userRepository.save(saveUser);

        saveUser = userRepository.findByIdOrElseThrow(saveUser.getId());

        return new UserCreateResponseDto(saveUser);
    }

    @Override //유저 조회 Read
    public UserFindResponseDto findUser(Long userId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        return new UserFindResponseDto(findUser);
    }

    @Transactional
    @Override //유저 수정 Update
    public UserUpdateResponseDto updateUser(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findByIdOrElseThrow(userId);
        user.setUserName(requestDto.getUserName());
        user.setEmail(requestDto.getEmail());
        return new UserUpdateResponseDto(user);
    }

    @Transactional
    @Override//유저 삭제 Delete
    public UserDeleteResponseDto deleteUser(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        userRepository.delete(user);
        return new UserDeleteResponseDto(user);
    }


}
