package com.example.todolist.repository.todo;

import com.example.todolist.entity.Todolist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;

public interface ToDoRepository extends JpaRepository<Todolist, Long> {

    default Todolist findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id));
    }

    List<Todolist> findByUserSignStatusTrue(Pageable pageable);


    //이름이랑 수정일로 찾기
    List<Todolist> findAllByUserIdAndLastModifiedAtBetween(Long userId, LocalDateTime startday, LocalDateTime endday);

    //내것만 찾기(유저아이디) -> 유저 객체 생성 후 만들기.
    List<Todolist> findAllByUserId(Long userId);

}