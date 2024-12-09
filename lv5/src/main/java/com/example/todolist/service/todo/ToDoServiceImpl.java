package com.example.todolist.service.todo;

import com.example.todolist.Exception.IdValueNotFoundException;
import com.example.todolist.Exception.InvalidAccessException;
import com.example.todolist.Exception.InvalidInputException;
import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.entity.ToDoList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.todolist.repository.todo.ToDoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {this.toDoRepository=toDoRepository;}

    @Override
    public ToDoListCreateResponseDto saveToDo(ToDoListCreateRequestDto requestDto) {
        if(requestDto.getUserId()==null||requestDto.getPassword()==null||requestDto.getName()==null||requestDto.getTitle()==null||requestDto.getContents()==null){
            throw new InvalidInputException("InvalidInputException");
        }
        ToDoList todo = new ToDoList(requestDto.getUserId(), requestDto.getPassword(), requestDto.getName(), requestDto.getTitle(), requestDto.getContents());

        return toDoRepository.saveToDo(todo);
    }

    @Override
    public List<ToDoListFindResponseDto> findAllTodo(int pageNumber, int pageSize) {
        return toDoRepository.findAllToDo(pageNumber,pageSize);
    }


    @Override
    public List<ToDoListFindResponseDto> findMyTodo(Long userId) {
        List<ToDoListFindResponseDto> todol = toDoRepository.findMyToDo(userId);
        return todol;
    }

    @Override
    public ToDoListFindResponseDto findToDoById(Long id) {
        ToDoList todo = toDoRepository.findToDoById(id);
        return new ToDoListFindResponseDto(todo);
    }


    @Override
    public ToDoListFindResponseDto updateToDo(Long id, Long userId, String password, String name, String contents) {

        //필수 입력값 null 검증
        if(id==null||userId==null|password==null|name==null||contents==null) {
            throw new InvalidInputException("required value.");
        }

        //유저 id와 password가 맞는지 확인.
        ToDoList todo = toDoRepository.findToDoById(id);
        if(todo.getUserId().equals(userId)&&todo.getPassword().equals(password)){
            int updateRow = toDoRepository.updateTodo(id,userId,name,contents, LocalDate.now());
            if(updateRow==0){
                throw new IdValueNotFoundException("list id 값 불일치");
            }
            todo = toDoRepository.findToDoById(id);
        } else {
            throw new InvalidAccessException("user_id, password not matched");
        }

        return new ToDoListFindResponseDto(todo);

    }

    @Override
    public void deleteToDo(Long id,Long userId, String password) {
        ToDoList todo = toDoRepository.findToDoById(id);

        if(todo==null) {
            throw new InvalidInputException("Does not exist id = " + id);
        }
        if(!todo.getPassword().equals(password)||!todo.getUserId().equals(userId)){
            throw new InvalidAccessException("user_id, password not matched");
        }

        toDoRepository.deleteToDo(id);

    }

}
