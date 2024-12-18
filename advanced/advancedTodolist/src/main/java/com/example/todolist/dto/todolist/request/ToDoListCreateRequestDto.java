package com.example.todolist.dto.todolist.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ToDoListCreateRequestDto {

    @NotBlank(message = "제목은 필수값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 200, message = "내용은 200글자 이내여야 합니다.")
    private String contents;
}
