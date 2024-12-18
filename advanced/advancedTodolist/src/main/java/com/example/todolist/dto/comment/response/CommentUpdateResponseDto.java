package com.example.todolist.dto.comment.response;

import com.example.todolist.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentUpdateResponseDto {

    public static final String UPDATE_SUCCESS = "업데이트 성공";
    Long todolistId;
    Long id;
    String contents;
    LocalDateTime createAt;
    LocalDateTime modifyAt;

    public CommentUpdateResponseDto(Long todolistId, Comment comment) {
        this.todolistId = todolistId;
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.createAt = comment.getCreatedAt();
        this.modifyAt = comment.getLastModifiedAt();
    }

    public String getUpdateSuccess() {
        return UPDATE_SUCCESS;
    }
}
