package com.example.todolist.entity;

import com.example.todolist.entity.base.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "todolist_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Todolist toDoList;

    @Setter
    @Column(nullable = false, length = 50)
    private String contents;

    public Comment() {
    }

    public Comment(Todolist toDoList, String contents) {
        this.toDoList = toDoList;
        this.contents = contents;
    }
}
