package com.controllers.admin;

import com.controllers.dto.TeacherDTO;
import com.db.entities.Teacher;
import com.db.repository.RoleRepository;
import com.db.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUsersController {

    private final TeacherRepository teacherRepository;
    private final RoleRepository roleRepository;

    public AdminUsersController(TeacherRepository teacherRepository, RoleRepository roleRepository) {
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/allTeachers")
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream().map(TeacherDTO::new).toList();
    }

    @PostMapping("/addTeacher")
    public void addTeacher(@RequestBody TeacherDTO teacherDTO) {
        if (teacherRepository.getFirstByUsername(teacherDTO.getUsername()) == null)
            teacherRepository.save(new Teacher(teacherDTO, roleRepository.getFirstByAuthority("ROLE_TEACHER")));
    }

    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody Long id) {
        teacherRepository.deleteTeacherById(id);
    }
}
