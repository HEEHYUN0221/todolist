package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table
public class ToDoListLv1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long list_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 200)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ToDoListLv1() {
    }

    public ToDoListLv1(String contents, String title, String name) {
        this.contents = contents;
        this.title = title;
        this.name = name;
    }
}
