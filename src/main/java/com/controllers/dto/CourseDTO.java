package com.controllers.dto;

import com.db.entities.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDTO {

    private Long id;
    private String content;
    private String targetGroup;

    public CourseDTO(Course course) {
        id = course.getId();
        content = course.getContent();
        targetGroup = course.getTargetGroup();
    }
}
