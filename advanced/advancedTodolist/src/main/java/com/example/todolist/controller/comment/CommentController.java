package com.example.todolist.controller.comment;

import com.example.todolist.common.Const;
import com.example.todolist.dto.comment.request.CommentCreateRequestDto;
import com.example.todolist.dto.comment.request.CommentUpdateRequestDto;
import com.example.todolist.dto.comment.response.CommentCreateResponseDto;
import com.example.todolist.common.session.LoginUserSession;
import com.example.todolist.dto.comment.response.CommentDeleteResponseDto;
import com.example.todolist.dto.comment.response.CommentFindAllResponseDto;
import com.example.todolist.dto.comment.response.CommentUpdateResponseDto;
import com.example.todolist.service.comment.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todolist/{todolistId}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> createComment(@PathVariable("todolistId") Long todolistId, @RequestBody CommentCreateRequestDto requestDto, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(commentService.saveComments(todolistId,user.getUser(), requestDto.getContents()), HttpStatus.CREATED);
    }

    @GetMapping("/comments")
    public ResponseEntity <List<CommentFindAllResponseDto>> findAllComment(@PathVariable ("todolistId") Long todolistId, @PageableDefault(size=10) Pageable pageable) {

        return new ResponseEntity<>(commentService.findAllComments(todolistId,pageable),HttpStatus.OK);

    }

    @PatchMapping("/comments/{commentsId}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(
            @PathVariable("todolistId") Long todolistId,
            @PathVariable Long commentsId,
            @RequestBody CommentUpdateRequestDto requestDto,
            HttpServletRequest request
            ) {
        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(
                commentService.updateComments(
                        user.getUser(), todolistId, commentsId, requestDto.getContent()),
                HttpStatus.OK);

    }

    @DeleteMapping("/comments/{commentsId}")
    public ResponseEntity<CommentDeleteResponseDto> deleteComment(
            @PathVariable("todolistId") Long todolistId,
            @PathVariable Long commentsId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        LoginUserSession user = (LoginUserSession) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(
                commentService.deleteComments(user.getUser(),commentsId),HttpStatus.OK);
    }

}
