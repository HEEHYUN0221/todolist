package com.example.todolist.dto.comment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequestDto {

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 50, message = "50자 이내로 입력해주세요.")
    private String contents;

    public CommentCreateRequestDto() {
    }
}
