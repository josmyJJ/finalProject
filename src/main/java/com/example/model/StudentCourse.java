package com.example.model;

import javax.persistence.*;

@Entity
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_course_id")
    private long studentCourseId;

    @Column(name = "student_id")
    private long studentId;

    @Column(name = "course_id")
    private long courseId;
}
