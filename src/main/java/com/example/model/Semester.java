package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Semester")
public class Semester {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "smester_id")
    private  long id;

    private String name;

    @OneToMany(mappedBy="semester")
    private Collection<Class> classes;

    @OneToMany(mappedBy="semester")
    private Collection<Grade> grades;


    public Semester() {
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

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    public Collection<Class> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }


}
