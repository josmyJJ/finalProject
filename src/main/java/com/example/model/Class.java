package com.example.model;
import com.example.beckend.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Classe")
public class Class {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "class_id")
    private  long id;

    private String crnNumber;
    private int courseNumber;
    private String subjectCode;
//    private String instructor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy="classObject")
    private Collection<Student> students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Class() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getCrnNumber() {
        return crnNumber;
    }

    public void setCrnNumber(String crnNumber) {
        this.crnNumber = crnNumber;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

//    public String getInstructor() {
//        return instructor;
//    }
//
//    public void setInstructor(String instructor) {
//        this.instructor = instructor;
//    }
}
