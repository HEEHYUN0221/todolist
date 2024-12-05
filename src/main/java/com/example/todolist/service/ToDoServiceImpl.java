package com.example.todolist.service;

import com.example.todolist.dto.ToDoListRequestDto;
import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.entity.ToDoList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.todolist.repository.ToDoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {this.toDoRepository=toDoRepository;}

    @Override
    public ToDoListResponseDto saveToDo(ToDoListRequestDto requestDto) {
        ToDoList todo = new ToDoList(requestDto.getUserId(), requestDto.getName(), requestDto.getTitle(), requestDto.getContents(), requestDto.getPassword());
        return toDoRepository.saveToDo(todo);
    }

    @Override
    public List<ToDoListResponseDto> findAllTodo() {
        return toDoRepository.findAllToDo();
    }

    @Override
    public ToDoListResponseDto findToDoById(Long id) {
        ToDoList todo = toDoRepository.findToDoById(id);

        if(todo==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        return new ToDoListResponseDto(todo);
    }

    //내용하고, 이름만 변경 수정일은 메소드 호출하면 바뀌어야
    @Override
    public ToDoListResponseDto updateToDo(Long id,String userId, String password,String name, String contents) {

        //입력값 검증
        if(id==null||password==null||name==null||contents==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"required value.");
        }

        //패스워드 검증, 검증하려면 db에서 패스워드 빼와야해 그럼 find해서 가져와
        ToDoList todo = toDoRepository.findToDoById(id);
        if(todo.getPassword().equals(password)|| Objects.equals(todo.getUserId(), userId)){
            int updateRow = toDoRepository.updateTodo(id,name,contents, LocalDate.now());
            todo = toDoRepository.findToDoById(id);
            if(updateRow==0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No data has been modified");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user_id, password not matched");
        }

        return new ToDoListResponseDto(todo);

    }

    @Override
    public void deleteToDo(Long id,String userId, String password) {
        ToDoList todo = toDoRepository.findToDoById(id);

        if(todo==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        if(!todo.getPassword().equals(password)||!todo.getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Password not match. ");
        }

        toDoRepository.deleteToDo(id);

    }

}
