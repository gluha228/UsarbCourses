package com.controllers.teacher;

import com.db.entities.Course;
import com.db.entities.Progress;
import com.db.repository.CourseRepository;
import com.db.repository.ProgressRepository;
import com.db.repository.TeacherRepository;

import java.security.Principal;
import java.util.List;

public class TeacherProgressController {
    private final ProgressRepository progressRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherProgressController(ProgressRepository progressRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.progressRepository = progressRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public List<Progress> getAllProgresses(Principal principal, Long courseID) {
        Course course = courseRepository.getById(courseID);
        if (course.getAuthor().getUsername() != principal.getName()) return null;
        return course.getProgresses();
    }
}
