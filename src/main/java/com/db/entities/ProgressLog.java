package com.db.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressLog {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String description;
    @JsonBackReference
    @ManyToOne
    private Progress progress;
}
