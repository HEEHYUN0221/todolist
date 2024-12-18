package com.example.todolist.service.todo;



import com.example.todolist.Exception.InvalidAccessException;
import com.example.todolist.dto.todolist.request.ToDoListCreateRequestDto;
import com.example.todolist.dto.todolist.response.*;
import com.example.todolist.entity.Todolist;
import com.example.todolist.entity.User;
import com.example.todolist.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.todolist.repository.todo.ToDoRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    //투두리스트 저장
    @Override
    public ToDoListCreateResponseDto saveToDo(ToDoListCreateRequestDto requestDto,Long userId) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Todolist saveToDo = new Todolist(
                requestDto.getTitle(),
                requestDto.getContents()
        );

        saveToDo.setUser(user);

        toDoRepository.save(saveToDo);

        return new ToDoListCreateResponseDto(toDoRepository.findByIdOrElseThrow(saveToDo.getId()));
    }

    //투두리스트 전체 조회
    @Override
    public List<ToDoListAllFindResponseDto> findAllTodo(Pageable pageable) {

    return toDoRepository.findAll(pageable)
            .stream()
            .map(ToDoListAllFindResponseDto::toDto)
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
    public List<ToDoListMineFindResponseDto> findMyTodo(Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);

        return toDoRepository.findAllByUserId(findUser.getId()).stream().map(ToDoListMineFindResponseDto::toDto).toList();

    }

    @Override
    public ToDoListFindResponseDto findToDoById(Long id) {
        return new ToDoListFindResponseDto(toDoRepository.findByIdOrElseThrow(id));
    }


    @Transactional
    @Override
    public ToDoListUpdateResponseDto updateToDo(Long id, Long userId, String title, String contents) {
        Todolist todo = toDoRepository.findByIdOrElseThrow(id);
        todo.setTitle(title);
        todo.setContents(contents);
        User user = todo.getUser();
        if(!user.getId().equals(userId)){
            throw new InvalidAccessException("userId가 일치하지 않습니다.");
        }
        toDoRepository.saveAndFlush(todo);
        user.setLastModifyToDoList(todo.getLastModifiedAt());
        todo.setUser(user);
        return new ToDoListUpdateResponseDto(todo);
    }

    @Transactional
    @Override
    public ToDoListDeleteResponseDto deleteToDo(Long id, Long userId) {
        Todolist todo = toDoRepository.findByIdOrElseThrow(id);
        if(!todo.getUser().getId().equals(userId)){
            throw new InvalidAccessException("userId가 일치하지 않습니다.");
        }
        toDoRepository.delete(todo);
        return new ToDoListDeleteResponseDto(todo.getTitle());
    }

}
