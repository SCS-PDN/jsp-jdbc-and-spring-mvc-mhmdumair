package com.university.dao;

import com.university.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Course> getAllCourses() {
        return jdbcTemplate.query("SELECT * FROM courses", new BeanPropertyRowMapper<>(Course.class));
    }
}
