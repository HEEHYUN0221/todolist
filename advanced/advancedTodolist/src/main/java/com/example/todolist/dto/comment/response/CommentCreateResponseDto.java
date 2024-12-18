package com.example.todolist.dto.comment.response;

import com.example.todolist.entity.Comment;
import com.example.todolist.entity.Todolist;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponseDto {

    public static final String CREATE_SUCCESS = "댓글 생성 완료";
    Long todolistId;
    String contents;
    String userName;
    LocalDateTime createDate;

    public CommentCreateResponseDto(Todolist todo, Comment comment) {
        this.todolistId = todo.getId();
        this.contents = comment.getContents();
        this.userName = todo.getUser().getUserName();
        this.createDate = comment.getCreatedAt();
    }

    public String getCreateSuccess() {
        return CREATE_SUCCESS;
    }
}
