package com.controllers.dto;

import com.db.entities.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class TeacherDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.username = teacher.getUsername();
        this.password = null;
        this.name = teacher.getName();
        this.surname = teacher.getSurname();
    }
}
