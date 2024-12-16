package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Table
public class ToDoList extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 200)
    private String contents;


    public ToDoList() {
    }

    public ToDoList(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
