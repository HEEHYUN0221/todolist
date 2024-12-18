package com.example.todolist.dto.comment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateRequestDto {
    @Size(max = 50, message = "50글자 이내여야 합니다.")
    @NotBlank(message = "값을 비울 수 없습니다.")
    String content;

    public CommentUpdateRequestDto() {
    }
}
