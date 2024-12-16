package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //유저 식별자

    @Setter
    @Column(nullable = false,unique = true)
    private String userName;

    @Setter
    @Column(unique = true)
    private String email;

    @Setter
    @Column
    private LocalDateTime lastModifyToDoList;

    public User() {
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}




