package com.example.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Major {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "major_id")
    private  long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "major", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Student> students;

    @OneToMany(mappedBy = "major", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Course> courses;

    private String name;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Collection<Course> getCourses() {
        return courses;
    }


}
