package repository;

import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.entity.ToDoList;

import java.util.List;

public interface ToDoRepository {

    ToDoList saveToDo(ToDoList todo);

    List<ToDoListResponseDto> findAllToDo();

    ToDoList findToDoById(Long id);

    void deleteToDo(Long id);
}
