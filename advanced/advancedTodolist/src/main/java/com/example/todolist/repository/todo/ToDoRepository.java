package com.example.todolist.repository.todo;

import com.example.todolist.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDoList, Long> {

    default ToDoList findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id));
    }


    //이름이랑 수정일로 찾기
    List<ToDoList> findAllByUserIdAndLastModifiedAtBetween(Long userId, LocalDateTime startday, LocalDateTime endday);

    //내것만 찾기(유저아이디) -> 유저 객체 생성 후 만들기.
    List<ToDoList> findAllByUserId(Long userId);

}