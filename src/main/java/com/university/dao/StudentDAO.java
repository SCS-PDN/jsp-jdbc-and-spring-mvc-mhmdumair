package com.university.dao;

import com.university.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Student validateStudent(String email, String password) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM students WHERE email=? AND password=?",
                    new Object[]{email, password},
                    new BeanPropertyRowMapper<>(Student.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
