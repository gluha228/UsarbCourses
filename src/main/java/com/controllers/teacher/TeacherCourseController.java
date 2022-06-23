package com.controllers.teacher;

import com.controllers.dto.CourseDTO;
import com.db.entities.Course;
import com.db.repository.CourseRepository;
import com.db.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/teacher")
public class TeacherCourseController {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final Logger logger = LoggerFactory.getLogger(TeacherCourseController.class);

    public TeacherCourseController(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/addCource")
    private void addCourse(@RequestBody CourseDTO courseDTO, Principal principal) {
        courseRepository.save(new Course(courseDTO, teacherRepository.getFirstByUsername(principal.getName())));
    }

    @DeleteMapping("/deleteCourse")
    private void deleteCourse(@RequestBody Long id, Principal principal) {
        if (Objects.equals(courseRepository.getById(id).getAuthor().getUsername(), principal.getName()))
            courseRepository.deleteById(id);
    }

    @GetMapping("/getAllCourses")
    private List<CourseDTO> getAllCourses(Principal principal) {
        logger.info("getAllCourses");
        return teacherRepository.getFirstByUsername(principal.getName()).getCourses().stream().map(CourseDTO::new).toList();
    }

    @PostMapping("/getCourse")
    private Course getCourse(@RequestBody Long id, Principal principal) {
        Course course = courseRepository.getById(id);
        if (!Objects.equals(course.getAuthor().getUsername(), principal.getName())) return null;
        return course;
    }
}
