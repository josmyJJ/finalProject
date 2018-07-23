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

    private int departmentID;
    private String name;

    @OneToMany(mappedBy = "department")

    private Collection<Major> majors;

    public int getDepartmentID() {
        return departmentID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
