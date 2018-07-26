package com.example.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="Classroom")
public class Classroom {
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private  long id;

    private String buildingName;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    private String roomNumber;
    private int maxCapacity;



    @OneToMany(mappedBy="classroom")

    private Collection<Class> classes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;



    @ManyToMany
    @JoinTable
            (
                    name = "classroom_course",
                    joinColumns = { @JoinColumn(name="classroom_id", referencedColumnName = "classroom_id") },
                    inverseJoinColumns = { @JoinColumn(name="course_id", referencedColumnName = "course_id") }
            )
    private List<Course> courses;


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
