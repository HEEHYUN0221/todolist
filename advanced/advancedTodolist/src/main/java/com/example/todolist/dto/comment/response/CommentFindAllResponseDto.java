package com.example.todolist.dto.comment.response;

import com.example.todolist.entity.Comment;
import com.example.todolist.entity.Todolist;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentFindAllResponseDto {
    Long todolistId;
    String contents;
    String userName;
    LocalDateTime createDate;
    LocalDateTime modifyDate;

    public CommentFindAllResponseDto(Todolist todo, Comment comment) {
        this.todolistId = todo.getId();
        this.contents = comment.getContents();
        this.userName = todo.getUser().getUserName();
        this.createDate = comment.getCreatedAt();
        this.modifyDate = comment.getLastModifiedAt();
    }

    public static CommentFindAllResponseDto toDto(Comment comment) {
        return new CommentFindAllResponseDto(
                comment.getToDoList().getId(),
                comment.getContents(),
                comment.getToDoList().getUser().getUserName(),
                comment.getCreatedAt(),
                comment.getLastModifiedAt()
        );
    }


}
