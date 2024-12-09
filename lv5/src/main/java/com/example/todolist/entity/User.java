package com.example.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class User {
    @Setter
    Long userId;
    String userName;
    String email;
    @Setter
    LocalDate registDate;
    LocalDate lastModifyDate;

    public User(String user_name, String email, LocalDate registDate) {
        this.userName = user_name;
        this.email = email;
        this.registDate = registDate;
    }

    public User(String user_name, String email) {
        this.userName = user_name;
        this.email = email;
    }
}




