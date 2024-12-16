package com.example.todolist.repository.todo;

import com.example.todolist.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ToDoRepository extends JpaRepository<ToDoList, Long> {

    default ToDoList findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id));
    }

    //생성


    //모두찾기

    //이름이랑 수정일로 찾기

    //내것만 찾기(유저아이디)

    //이름이랑 내용 바꾸기

    //삭제하기

}