package com.example.todolist.service.todo;



import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.entity.ToDoList;
import jakarta.transaction.Transactional;
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
        ToDoList saveToDo = new ToDoList(
                requestDto.getName(),
                requestDto.getTitle(),
                requestDto.getContents()
        );
        toDoRepository.save(saveToDo);

        ToDoList dto = toDoRepository.findByIdOrElseThrow(saveToDo.getId());

        return new ToDoListCreateResponseDto(toDoRepository.findByIdOrElseThrow(saveToDo.getId()));
    }

    @Override
    public List<ToDoListFindResponseDto> findAllTodo() {
    return toDoRepository.findAll()
            .stream()
            .map(ToDoListFindResponseDto::toDto)
            .toList();
    }

    @Override
    public List<ToDoListFindResponseDto> findToDoListNameUpdateDate(String name, LocalDate modifyDate) {

        LocalDateTime startday = modifyDate.atStartOfDay();
        LocalDateTime endday = modifyDate.atTime(23,59,59);

        return toDoRepository.findAllByNameAndLastModifiedAtBetween(name, startday,endday)
                .stream()
                .map(ToDoListFindResponseDto::toDto)
                .toList();
    }


    //유저 테이블 수정 후 수정예정
    @Override
    public List<ToDoListFindResponseDto> findMyTodo(Long userId) {
        return null;
//        List<ToDoListFindResponseDto> todol = toDoRepository.findMyToDo(userId);
//        return todol;
    }

    @Override
    public ToDoListFindResponseDto findToDoById(Long id) {
        return new ToDoListFindResponseDto(toDoRepository.findByIdOrElseThrow(id));
    }


    @Transactional
    @Override
    public ToDoListFindResponseDto updateToDo(Long id, String name, String contents) {
        ToDoList todo = toDoRepository.findByIdOrElseThrow(id);
        todo.setContents(contents);
        todo.setName(name);
        return new ToDoListFindResponseDto(todo);
    }

    @Override
    public void deleteToDo(Long id) {
        ToDoList todo = toDoRepository.findByIdOrElseThrow(id);
        toDoRepository.delete(todo);

    }

}
