package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Class")
public class Class {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "class_id")
    private  long id;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    private String crnNumber;

    private String time;
    private String semester;

    private int courseNumber;


    @ManyToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    private Collection<Student> students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;


    public Class() {
    }


    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    private String subjectCode;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

//    @ManyToMany(mappedBy = "classes", fetch = FetchType.LAZY)
//    private Collection<Student> students;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

//    public Collection<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Collection<Student> students) {
//        this.students = students;
//    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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
