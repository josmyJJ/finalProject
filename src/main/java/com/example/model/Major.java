package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Major {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "major_id")
    private  long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy="major")
    private Collection<Student> students;

    @OneToMany(mappedBy="major")
    private Collection<Course> courses;

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
}
