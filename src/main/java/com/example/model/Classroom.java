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
    private String roomNumber;
    private int maxCapacity;

    @OneToMany(mappedBy="classroom")
    private Collection<Class> classes;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Collection<Class> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }


}
