package com.example.todolist.service.comment;

import com.example.todolist.Exception.InvalidAccessException;
import com.example.todolist.dto.comment.response.CommentCreateResponseDto;
import com.example.todolist.dto.comment.response.CommentDeleteResponseDto;
import com.example.todolist.dto.comment.response.CommentFindAllResponseDto;
import com.example.todolist.dto.comment.response.CommentUpdateResponseDto;
import com.example.todolist.entity.Comment;
import com.example.todolist.entity.Todolist;
import com.example.todolist.entity.User;
import com.example.todolist.repository.comment.CommentRepository;
import com.example.todolist.repository.todo.ToDoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ToDoRepository toDoRepository;

    public CommentCreateResponseDto saveComments(Long listId, User user,String contents){

        Todolist todo = toDoRepository.findByIdOrElseThrow(listId);

        Comment saveComment = new Comment(todo,contents);

        commentRepository.save(saveComment);

        saveComment=commentRepository.findByIdOrElseThrow(saveComment.getId());

        return new CommentCreateResponseDto(todo,saveComment);

    }

    public List<CommentFindAllResponseDto> findAllComments(Long listId, Pageable pageable) {

        Todolist todo = toDoRepository.findByIdOrElseThrow(listId);

        return commentRepository.findAllByToDoList_Id(listId,pageable)
                .stream()
                .map(CommentFindAllResponseDto::toDto)
                .toList();
    }

    @Transactional
    public CommentUpdateResponseDto updateComments(User user,Long todolistId, Long commentsId, String content) {

        Todolist todo = toDoRepository.findByIdOrElseThrow(todolistId);

        Comment comment = commentRepository.findByIdOrElseThrow(commentsId);

        if(!comment.getToDoList().getUser().getId().equals(user.getId())){
            throw new InvalidAccessException("본인이 등록한 댓글만 수정할 수 있습니다.");
        }

        comment.setToDoList(todo);
        comment.setContents(content);

        commentRepository.saveAndFlush(comment);

        return new CommentUpdateResponseDto(todolistId,comment);
    }

    @Transactional
    public CommentDeleteResponseDto deleteComments(User user, Long commentsId) {

        Comment comment = commentRepository.findByIdOrElseThrow(commentsId);

        if(!comment.getToDoList().getUser().getId().equals(user.getId())){
            throw new InvalidAccessException("본인이 등록한 댓글만 수정할 수 있습니다.");
        }

        commentRepository.delete(comment);
        return new CommentDeleteResponseDto();

    }
}
