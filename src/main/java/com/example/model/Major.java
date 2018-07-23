package com.example.model;

import javax.persistence.*;


@Entity
@Table(name="Major")
public class Major {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  long id;

    private int majorID;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    public Major() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
