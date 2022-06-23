package com.db.entities;

import com.controllers.dto.CourseDTO;
import com.controllers.dto.TeacherDTO;
import com.db.repository.TeacherRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private String targetGroup;
    @ManyToOne
    @JsonBackReference
    private Teacher author;
    @JsonManagedReference
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Progress> progresses;

    public Course(CourseDTO courseDTO, Teacher author) {
        id = null;
        content = courseDTO.getContent();
        targetGroup = courseDTO.getTargetGroup();
        this.author = author;
        progresses = null;
    }
}
