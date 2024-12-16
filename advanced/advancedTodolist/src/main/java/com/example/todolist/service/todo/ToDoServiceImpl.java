package com.example.todolist.service.todo;



import com.example.todolist.Exception.InvalidAccessException;
import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.entity.ToDoList;
import com.example.todolist.entity.User;
import com.example.todolist.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.todolist.repository.todo.ToDoRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    @Override
    public ToDoListCreateResponseDto saveToDo(ToDoListCreateRequestDto requestDto) {

        User user = userRepository.findByIdOrElseThrow(requestDto.getUserId());

        ToDoList saveToDo = new ToDoList(
                requestDto.getTitle(),
                requestDto.getContents()
        );

        saveToDo.setUser(user);

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

        User findUser = userRepository.findUserByUserName(name);

        return toDoRepository.findAllByUserIdAndLastModifiedAtBetween(findUser.getId(), startday,endday)
                .stream()
                .map(ToDoListFindResponseDto::toDto)
                .toList();
    }


    @Override
    public List<ToDoListFindResponseDto> findMyTodo(Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);

        return toDoRepository.findAllByUserId(findUser.getId()).stream().map(ToDoListFindResponseDto::toDto).toList();

    }

    @Override
    public ToDoListFindResponseDto findToDoById(Long id) {
        return new ToDoListFindResponseDto(toDoRepository.findByIdOrElseThrow(id));
    }


    @Transactional
    @Override
    public ToDoListFindResponseDto updateToDo(Long id,Long userId, String title, String contents) {
        ToDoList todo = toDoRepository.findByIdOrElseThrow(id);
        todo.setTitle(title);
        todo.setContents(contents);
        User user = todo.getUser();
        if(!Objects.equals(user.getId(), userId)){
            throw new InvalidAccessException("userId가 일치하지 않습니다.");
        }
        toDoRepository.saveAndFlush(todo);
        user.setLastModifyToDoList(todo.getLastModifiedAt());
        todo.setUser(user);
        return new ToDoListFindResponseDto(todo);
    }

    @Transactional
    @Override
    public void deleteToDo(Long id) {
        ToDoList todo = toDoRepository.findByIdOrElseThrow(id);
        toDoRepository.delete(todo);
    }

}
