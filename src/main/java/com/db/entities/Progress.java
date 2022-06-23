package com.db.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Progress {
    @Id
    @GeneratedValue
    private Long id;
    private boolean isComplete;
    private int mark;
    @JsonManagedReference
    @OneToMany(mappedBy = "progress", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProgressLog> progressLogs;
    @JsonBackReference
    @ManyToOne
    private Course course;
    @JsonBackReference
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Student author;
}
