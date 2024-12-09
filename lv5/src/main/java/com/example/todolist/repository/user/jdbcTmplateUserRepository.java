package com.example.todolist.repository.user;

import com.example.todolist.Exception.IdValueNotFoundException;
import com.example.todolist.dto.user.response.UserCreateResponseDto;
import com.example.todolist.dto.user.response.UserFindResponseDto;
import com.example.todolist.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class jdbcTmplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public jdbcTmplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //유저 생성 Create
    @Override
    public UserCreateResponseDto createUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("user_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_name", user.getUserName());
        parameters.put("email", user.getEmail());
        parameters.put("regist_date", user.getRegistDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new UserCreateResponseDto(key.longValue(), user.getUserName(), user.getEmail(), user.getRegistDate());
    }

    //유저 조회 Read
    @Override
    public UserFindResponseDto findUser(Long userId) {
        List<UserFindResponseDto> result = jdbcTemplate.query("select * from user where user_id = ?", userRowMapper(), userId);
        return result.stream().findAny().orElseThrow(() -> new IdValueNotFoundException("Does not exist id = " + userId));
    }

    //유저 수정 Update
    @Override
    public int updateUser(Long userId, String name, String email) {
        return jdbcTemplate.update("update user set user_name = ?, EMAIL=? where user_id = ?", name, email, userId);
    }

    //유저 삭제 Delete
    @Override
    public void deleteUser(Long userId) {
        jdbcTemplate.update("delete from user where user_id = ?", userId);
    }

    private RowMapper<UserFindResponseDto> userRowMapper() {
        return new RowMapper<UserFindResponseDto>() {
            @Override
            public UserFindResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                LocalDate registDate = rs.getDate("regist_date").toLocalDate();
                LocalDate lastModifyDate = null;
                if (rs.getDate("last_modify_date") != null) {
                    lastModifyDate = rs.getDate("last_modify_date").toLocalDate();
                }

                return new UserFindResponseDto(
                        rs.getLong("user_id"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        registDate,
                        lastModifyDate
                );
            }
        };
    }


}
