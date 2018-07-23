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
    private Collection<Major> majors;

    @OneToMany(mappedBy = "department")
    private Collection<Instructor> instructors;


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
