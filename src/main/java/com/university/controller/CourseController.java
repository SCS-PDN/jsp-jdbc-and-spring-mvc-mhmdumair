package com.university.controller;

import com.university.dao.CourseDAO;
import com.university.dao.RegistrationDAO;
import com.university.model.Course;
import com.university.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private RegistrationDAO registrationDAO;

    @GetMapping("/courses")
    public String listCourses(Model model, HttpSession session) {
        if (session.getAttribute("student") == null) {
            return "redirect:/login";
        }
        List<Course> courses = courseDAO.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId,
                                 HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }

        try {
            if (registrationDAO.isAlreadyRegistered(student.getStudentId(), courseId)) {
                model.addAttribute("error", "You are already registered for this course");
            } else {
                boolean success = registrationDAO.register(student.getStudentId(), courseId);
                if (!success) {
                    model.addAttribute("error", "Registration failed. Please try again.");
                } else {
                    model.addAttribute("success", "Registration successful!");
                }
            }
        } catch (Exception e) {
            model.addAttribute("error", "System error during registration");
            e.printStackTrace();
        }

        // Reload courses
        List<Course> courses = courseDAO.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
}