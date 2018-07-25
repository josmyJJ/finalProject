package com.example.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name="Course")
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "course_id")
    private  long id;

    private int crn;
    private String subjectCode;
    private String name;
    private String detail;
    private int numOfCredits;

    @OneToMany(mappedBy="course")
    private Collection<Class> classes;

    @OneToMany(mappedBy="course")
    private Collection<Grade> grades;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
    private Major major;




    @OneToMany
    @JoinTable
            (
                    name = "StudentCourse",
                    joinColumns = { @JoinColumn(name="course_id", referencedColumnName = "course_id") },
                    inverseJoinColumns = { @JoinColumn(name="student_id", referencedColumnName = "student_id") }
            )
    private List<Student> studentList;

    public Course() {
    }


    public Course(int crn, String subjectCode, String name, String detail, int numOfCredits, Collection<Class> classes, Collection<Department> departments) {
        this.crn = crn;
        this.subjectCode = subjectCode;
        this.name = name;
        this.detail = detail;
        this.numOfCredits = numOfCredits;
        this.classes = classes;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getNumOfCredits() {
        return numOfCredits;
    }

    public void setNumOfCredits(int numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    public Collection<Class> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
