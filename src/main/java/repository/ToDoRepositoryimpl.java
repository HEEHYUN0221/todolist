package repository;

import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.entity.ToDoList;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ToDoRepositoryimpl implements ToDoRepository{

    private final Map<Long, ToDoList> toDoListMap = new HashMap<>();

    @Override
    public ToDoList saveToDo(ToDoList toDoList) {
        // 식별자
        Long todoId = toDoListMap.isEmpty() ? 1 : Collections.max(toDoListMap.keySet()) + 1;
        toDoList.setId(todoId);

        //리스트에 저장
        toDoListMap.put(todoId, toDoList);

        return toDoList;
    }

    @Override
    public List<ToDoListResponseDto> findAllToDo() {

        List<ToDoListResponseDto> allToDo = new ArrayList<>();

        for(ToDoList todo : toDoListMap.values()) {
            ToDoListResponseDto responseDto = new ToDoListResponseDto(todo);
            allToDo.add(responseDto);
        }
        return allToDo;
    }

    @Override
    public ToDoList findToDoById(Long id) {
        return toDoListMap.get(id);
    }

    @Override
    public void deleteToDo(Long id) {
            toDoListMap.remove(id);

    }
}
