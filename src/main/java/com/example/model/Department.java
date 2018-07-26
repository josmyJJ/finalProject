package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Department")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "department_id")
    private  long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private Collection<Student> students;

    @OneToMany(mappedBy = "department")
    private Collection<Class> classes;


    @OneToMany(mappedBy = "department")
    private Collection<Instructor> instructors;

    @OneToMany(mappedBy = "department")
    private Collection<Course> courses;

    @OneToMany(mappedBy="department")
    private Collection<Major> majors;

    public Collection<Class> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }

    public Collection<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Collection<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @OneToMany(mappedBy="department")
    private Collection<Classroom> classrooms;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(joinColumns = @JoinColumn(name="department_id"),
//            inverseJoinColumns = @JoinColumn(name="course_id"))
//    private Collection<Course> courses;


    public Department() {
    }

    public Department(String name, Collection<Student> students, Collection<Instructor> instructors, Collection<Course> courses) {
        this.name = name;
        this.students = students;
        this.instructors = instructors;
        this.courses = courses;
    }

    public Collection<Major> getMajors() {
        return majors;
    }

    public void setMajors(Collection<Major> majors) {
        this.majors = majors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public Collection<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Collection<Instructor> instructors) {
        this.instructors = instructors;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
