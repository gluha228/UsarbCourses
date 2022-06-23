package com.db.repository;

import com.db.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher getFirstByUsername(String username);
    void deleteTeacherById(Long id);
}