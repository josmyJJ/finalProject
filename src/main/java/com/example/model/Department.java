package com.example.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="Department")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "department_id")
    private  long id;

    private String departmentName;

    @OneToMany(mappedBy = "department")
    private Collection<Student> students;

    @OneToMany(mappedBy = "department")
    private Collection<Class> classes;


    @OneToMany(mappedBy = "department")
    private Collection<Instructor> instructors;

    @OneToMany(mappedBy = "department")
    private Collection<Course> courses;


    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Major> majors;

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


    public Collection<Major> getMajors() {
        return majors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public void setMajors(Set<Major> majors) {
        this.majors = majors;
    }


}
