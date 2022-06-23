package com.security.service;

import com.db.entities.*;
import com.db.repository.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final ProgressRepository progressRepository;
    private final ProgressLogRepository progressLogRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    public MyUserDetailsService(TeacherRepository teacherRepository, CourseRepository courseRepository, ProgressRepository progressRepository, ProgressLogRepository progressLogRepository, StudentRepository studentRepository, RoleRepository roleRepository) throws InterruptedException {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.progressRepository = progressRepository;
        this.progressLogRepository = progressLogRepository;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        initializer();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherRepository.getFirstByUsername(username);
    }

    private void initializer() throws InterruptedException {
        roleRepository.saveAll(Arrays.asList(new Role("ROLE_TEACHER"), new Role("ROLE_STUDENT")));
        Student student = new Student(1L, "user1", new BCryptPasswordEncoder().encode("password"), "liviu", "deleu",
                "IT21Z", new ArrayList<>());
        Teacher teacher = new Teacher(1L,
                "user", new BCryptPasswordEncoder().encode("password"), "lidia", "ivanovna",
                new HashSet<>(Collections.singletonList(new Role("ROLE_TEACHER"))), new ArrayList<>());

        Course course = new Course(1L, "content", "IT21Z", teacher, new ArrayList<>());
        Progress progress = new Progress(1L, false, 0, new ArrayList<>(), course, null);
        ProgressLog progressLog = new ProgressLog(1L, new Date(), "log description", progress);
        teacher.getCourses().add(course);
        course.getProgresses().add(progress);
        progress.getProgressLogs().add(progressLog);
        teacherRepository.save(teacher);
        studentRepository.save(student);
        progress = progressRepository.findAll().get(0);
        student = studentRepository.findAll().get(0);
        progress.setAuthor(student);
        progressRepository.save(progress);
        System.out.println("tt");
    }
}
