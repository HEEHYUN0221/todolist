package com.example.todolist.repository;

import com.example.todolist.dto.ToDoListResponseDto;
import com.example.todolist.entity.ToDoList;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class jdbcTmplateTodoRepository implements ToDoRepository{

    private final JdbcTemplate jdbcTemplate;

    public jdbcTmplateTodoRepository(DataSource dataSource) { this.jdbcTemplate=new JdbcTemplate(dataSource);}


    @Override
    public ToDoListResponseDto saveToDo(ToDoList todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todolist").usingGeneratedKeyColumns("list_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title",todo.getTitle());
        parameters.put("contents",todo.getContents());
        parameters.put("user_name",todo.getName());
        parameters.put("user_id",todo.getUserId());
        parameters.put("create_date",todo.getDate());
        parameters.put("modify_date",todo.getModifyDate());
        parameters.put("user_password",todo.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ToDoListResponseDto(key.longValue(),todo.getTitle(),todo.getContents(),todo.getName(),todo.getDate(),todo.getModifyDate());
    }

    @Override
    public List<ToDoListResponseDto> findAllToDo() {
        return jdbcTemplate.query("select * from todolist", todoRowMapperResp());
    }

    @Override
    public ToDoList findToDoById(Long id) {
        List<ToDoList> result = jdbcTemplate.query("select * from todolist where LIST_ID = ? order by modify_date desc, list_id desc", todoRowMapper(),id);
        return result.stream().findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id));
    }

    @Override
    public int updateTodo(Long id, String name, String contents, LocalDate modifyDate) {
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

                LocalDate modifyDate = rs.getDate("modify_date").toLocalDate();
                LocalDate createDate = rs.getDate("create_date").toLocalDate();

                return new ToDoList(
                        rs.getString("user_name"),
                        rs.getLong("list_id"),
                        rs.getString("user_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        modifyDate,
                        createDate,
                        rs.getString("user_password")
                );
            }
        };
    }

    private RowMapper<ToDoListResponseDto> todoRowMapperResp() {
        return new RowMapper<ToDoListResponseDto>() {
            @Override
            public ToDoListResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                LocalDate modifyDate = rs.getDate("modify_date").toLocalDate();
                LocalDate createDate = rs.getDate("create_date").toLocalDate();

                return new ToDoListResponseDto(
                        rs.getLong("list_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("user_name"),
                        modifyDate,
                        createDate
                );
            }
        };
    }

}
