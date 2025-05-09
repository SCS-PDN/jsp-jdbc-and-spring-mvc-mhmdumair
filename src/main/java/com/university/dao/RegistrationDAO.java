package com.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isAlreadyRegistered(int studentId, int courseId) {
        try {
            String sql = "SELECT COUNT(*) FROM registrations WHERE student_id=? AND course_id=?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, studentId, courseId);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(int studentId, int courseId) {
        try {
            String sql = "INSERT INTO registrations (student_id, course_id) VALUES (?, ?)";
            int rows = jdbcTemplate.update(sql, studentId, courseId);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}