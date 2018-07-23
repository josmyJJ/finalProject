package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private  long id;

    private String name;

    @OneToMany(mappedBy="subject")
    private Collection<Course> courses;

    @OneToMany(mappedBy="subject")
    private Collection<Class> classes;

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

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
