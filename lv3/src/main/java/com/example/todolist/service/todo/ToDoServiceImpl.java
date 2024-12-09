package com.example.todolist.service.todo;

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
        ToDoList todo = new ToDoList(requestDto.getUserId(), requestDto.getPassword(), requestDto.getName(), requestDto.getTitle(), requestDto.getContents());
        return toDoRepository.saveToDo(todo);
    }

    @Override
    public List<ToDoListFindResponseDto> findAllTodo() {
        return toDoRepository.findAllToDo();
    }

    //지정 유저의 아이디만 찾기, 매개변수 필요. request에 user_id(식별자)만 받아올지?
    @Override
    public List<ToDoListFindResponseDto> findMyTodo(Long userId) {
        return toDoRepository.findMyToDo(userId);
    }

    @Override
    public ToDoListFindResponseDto findToDoById(Long id) {
        ToDoList todo = toDoRepository.findToDoById(id);

        if(todo==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        return new ToDoListFindResponseDto(todo);
    }

    //내용하고, 이름만 변경 수정일은 메소드 호출하면 바뀌어야
    @Override
    public ToDoListFindResponseDto updateToDo(Long id, Long userId, String password, String name, String contents) {

        //입력값 검증
        if(id==null||name==null||contents==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"required value.");
        }

        //패스워드 검증, 검증하려면 db에서 패스워드 빼와야

        //이제 패스워드를 유저테이블에서 가져올거... 이 게시글을 작성한 유저의 정보를 가져와야함. 패스워드정보만?
        ToDoList todo = toDoRepository.findToDoById(id);
        if(todo.getUserId().equals(userId)){
            int updateRow = toDoRepository.updateTodo(id,userId,name,contents, LocalDate.now());
            todo = toDoRepository.findToDoById(id);
            if(updateRow==0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No data has been modified");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"user_id not matched");
        }

        return new ToDoListFindResponseDto(todo);

    }

    @Override
    public void deleteToDo(Long id,Long userId, String password) {
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
