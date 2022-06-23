package com.db.repository;

import com.db.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getFirstByUsername(String username);
}