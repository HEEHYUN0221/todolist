package com.example.todolist.service.user;

import com.example.todolist.Exception.DataNotModifyException;
import com.example.todolist.Exception.InvalidInputException;
import com.example.todolist.dto.user.request.UserCreateRequestDto;
import com.example.todolist.dto.user.request.UserUpdateRequestDto;
import com.example.todolist.dto.user.response.UserCreateResponseDto;
import com.example.todolist.dto.user.response.UserFindResponseDto;
import com.example.todolist.entity.User;
import com.example.todolist.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {this.userRepository=userRepository;}

    @Override//유저 생성 Create
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        if(requestDto.getUserName()==null|requestDto.getEmail()==null) {
            throw new InvalidInputException("name or email required");
        }
        if(!userEmailValidate(requestDto.getEmail())){
            throw new InvalidInputException("email form \"xxx@xxxx.xxx\"");
        }
        User user = new User(requestDto.getUserName(), requestDto.getEmail());
        user.setRegistDate(LocalDate.now());
        return userRepository.createUser(user);
    }

    @Override //유저 조회 Read
    public UserFindResponseDto findUser(Long userId) {
        return userRepository.findUser(userId);
    }

    @Override //유저 수정 Update
    public UserFindResponseDto updateUser(Long userId, UserUpdateRequestDto requestDto) {
        if((requestDto.getUserName()==null)||(requestDto.getEmail()==null)) {
            throw new InvalidInputException("required value.");
        }

        int updateRow = userRepository.updateUser(userId,requestDto.getUserName(), requestDto.getEmail());

        if(updateRow==0){
            throw new DataNotModifyException("No data has been modified");
        }

        return userRepository.findUser(userId);
    }

    @Override//유저 삭제 Delete
    public void deleteUser(Long userId) {
        UserFindResponseDto user = userRepository.findUser(userId);

        userRepository.deleteUser(userId);
    }

    private boolean userEmailValidate(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }







}
