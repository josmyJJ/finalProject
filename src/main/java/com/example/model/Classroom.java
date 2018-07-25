package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private  long id;

    private String buildingName;
    private String roomNumbner;
    private int maxCapacity;

    @OneToMany(mappedBy="classroom")
    private Collection<Class> classes;

}
