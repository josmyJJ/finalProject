package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Grade")
public class Grade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private  long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
}
