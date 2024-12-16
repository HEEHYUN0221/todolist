package com.example.todolist.service.todo;

import com.example.todolist.Exception.DataNotModifyException;
import com.example.todolist.Exception.InvalidAccessException;
import com.example.todolist.Exception.InvalidInputException;
import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.entity.ToDoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.todolist.repository.todo.ToDoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public ToDoListCreateResponseDto saveToDo(ToDoListCreateRequestDto requestDto) {
        log.info("name = {}title : {}contents : {}",requestDto.getName(),requestDto.getTitle(),requestDto.getContents());
        ToDoList saveToDo = new ToDoList(
                requestDto.getName(),
                requestDto.getTitle(),
                requestDto.getContents()
        );
        toDoRepository.save(saveToDo);

//        ToDoList todo = toDoRepository.findByIdOrElseThrow(s)

        ToDoList dto = toDoRepository.findByIdOrElseThrow(saveToDo.getId());
        log.info(dto.getCreatedAt()+"");

        return new ToDoListCreateResponseDto(toDoRepository.findByIdOrElseThrow(saveToDo.getId()));
    }

    @Override
    public List<ToDoListFindResponseDto> findAllTodo(int pageNumber, int pageSize) {
//        return toDoRepository.findAllToDo(pageNumber, pageSize);
    return null;
    }

    @Override
    public List<ToDoListFindResponseDto> findToDoListNameUpdateDate(String name, LocalDate modifyDate) {
        return null;
//        return toDoRepository.findToDoListNameUpdateDate(name,modifyDate);
    }


    @Override
    public List<ToDoListFindResponseDto> findMyTodo(Long userId) {
        return null;
//        List<ToDoListFindResponseDto> todol = toDoRepository.findMyToDo(userId);
//        return todol;
    }

    @Override
    public ToDoListFindResponseDto findToDoById(Long id) {
        return null;
//        ToDoList todo = toDoRepository.findToDoById(id);
//        return new ToDoListFindResponseDto(todo);
    }


    @Override
    public ToDoListFindResponseDto updateToDo(Long id, Long userId, String password, String name, String contents) {

        return null;

//        //유저 id와 password가 맞는지 확인.
//        ToDoList todo = toDoRepository.findToDoById(id);
//        if (todo.getUserId().equals(userId) && todo.getPassword().equals(password)) {
//            int updateRow = toDoRepository.updateTodo(id, userId, name, contents, LocalDateTime.now());
//            if (updateRow == 0) {
//                throw new DataNotModifyException("No data has been modified");
//            }
//            todo = toDoRepository.findToDoById(id);
//        } else {
//            throw new InvalidAccessException("user_id, password not matched");
//        }
//
//        return new ToDoListFindResponseDto(todo);

    }

    @Override
    public void deleteToDo(Long id, Long userId, String password) {
//        ToDoList todo = toDoRepository.findToDoById(id);
//
//
//        if (!todo.getPassword().equals(password) || !todo.getUserId().equals(userId)) {
//            throw new InvalidAccessException("user_id, password not matched");
//        }
//
//        toDoRepository.deleteToDo(id);

    }

}
