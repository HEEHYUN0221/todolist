package com.example.todolist.repository.todo;

import com.example.todolist.Exception.IdValueNotFoundException;
import com.example.todolist.dto.todolist.response.ToDoListCreateResponseDto;
import com.example.todolist.dto.todolist.response.ToDoListFindResponseDto;
import com.example.todolist.entity.ToDoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class jdbcTmplateTodoRepository implements ToDoRepository {

    private final JdbcTemplate jdbcTemplate;

    public jdbcTmplateTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ToDoListCreateResponseDto saveToDo(ToDoList todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todolist").usingGeneratedKeyColumns("list_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", todo.getTitle());
        parameters.put("contents", todo.getContents());
        parameters.put("user_name", todo.getName());
        parameters.put("user_id", todo.getUserId());
        parameters.put("create_date", todo.getDate());
        parameters.put("modify_date", todo.getModifyDate());
        parameters.put("user_password", todo.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ToDoListCreateResponseDto(key.longValue(), todo.getUserId(), todo.getTitle(), todo.getContents(), todo.getName(), todo.getDate(), todo.getModifyDate());
    }

    @Override
    public List<ToDoListFindResponseDto> findAllToDo(int pageNumber, int pageSize) {
        return jdbcTemplate.query("select * from todolist order by modify_date desc, list_id desc, list_id desc limit ? offset ?", todoRowMapperResp(), pageSize, (pageNumber - 1) * pageSize);
    }

    @Override
    public List<ToDoListFindResponseDto> findToDoListNameUpdateDate(String name, LocalDate modifyDate) {
        String modifyDateStr = modifyDate+"%";
        System.out.println(modifyDateStr);
        return jdbcTemplate.query("select * from todolist where user_name = ? and modify_date like ?", todoRowMapperResp(),name,modifyDateStr);
    }

    @Override
    public List<ToDoListFindResponseDto> findMyToDo(Long userId) {
        return jdbcTemplate.query("select * from todolist t join user u on t.USER_ID=u.USER_ID where u.USER_ID = ? order by t.modify_date desc, t.list_id desc", todoRowMapperResp(), userId);
    }

    @Override
    public ToDoList findToDoById(Long id) {
        List<ToDoList> result = jdbcTemplate.query("select * from todolist where LIST_ID = ?", todoRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new IdValueNotFoundException("Invalid list ID"));
    }

    @Override
    public int updateTodo(Long id, Long userID, String name, String contents, LocalDateTime modifyDate) {
        jdbcTemplate.update("update user set LAST_MODIFY_DATE = ? where USER_ID = ?", modifyDate, userID);
        return jdbcTemplate.update("update todolist set user_name = ?, contents = ?, MODIFY_DATE = ? where list_id = ?", name, contents, modifyDate, id);
    }

    @Override
    public void deleteToDo(Long id) {
        jdbcTemplate.update("delete from todolist where LIST_ID= ?", id);
    }


    private RowMapper<ToDoList> todoRowMapper() {
        return new RowMapper<ToDoList>() {
            @Override
            public ToDoList mapRow(ResultSet rs, int rowNum) throws SQLException {

                LocalDateTime modifyDate = rs.getTimestamp("modify_date").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();

                return new ToDoList(
                        rs.getString("user_name"),
                        rs.getLong("list_id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        createDate,
                        modifyDate,
                        rs.getString("user_password")
                );
            }
        };
    }

    private RowMapper<ToDoListFindResponseDto> todoRowMapperResp() {
        return new RowMapper<ToDoListFindResponseDto>() {
            @Override
            public ToDoListFindResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                LocalDateTime modifyDate = rs.getTimestamp("modify_date").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();

                return new ToDoListFindResponseDto(
                        rs.getLong("list_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("user_name"),
                        createDate,
                        modifyDate
                );
            }
        };
    }

}
