package com.security.controllers;

import com.db.entities.Teacher;
import com.db.repository.StudentRepository;
import com.db.repository.TeacherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public TestController(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/tt")
    private List<?> getTeacher() {
        System.out.println("ttt");
        UserDetails user = new Teacher();
        List<UserDetails> users = (List<UserDetails>) ((List<?>)teacherRepository.findAll());
        List<UserDetails> students = (List<UserDetails>) ((List<?>)studentRepository.findAll());
        users.addAll(students);
        //System.out.println(teachers.get(0).getCourses());
        return users;
    }
}
