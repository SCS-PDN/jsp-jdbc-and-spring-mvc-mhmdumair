package com.university.interceptor;

import com.university.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        Student student = (Student) request.getSession().getAttribute("student");

        // Log login attempts
        if (request.getRequestURI().equals("/login") &&
                request.getMethod().equalsIgnoreCase("POST")) {
            String email = request.getParameter("email");
            logger.info("Login attempt - Email: {}", email);
        }

        // Log course registrations
        if (request.getRequestURI().startsWith("/register/") && student != null) {
            int courseId = Integer.parseInt(request.getRequestURI().split("/")[2]);
            logger.info("Course registration - Student ID: {}, Course ID: {}",
                    student.getStudentId(), courseId);
        }

        return true;
    }
}