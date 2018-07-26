package com.example.repository;

import com.example.model.*;
import com.example.model.Class;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ClassRepository extends CrudRepository<Class, Long>{

    ArrayList<Class> findAllBySemester(String semester);
    ArrayList<Class> findAllBySemesterAndInstructor(String semester, Instructor Instructor);
    ArrayList<Class> findAllBySemesterAndSubject(String semester, Subject subject);
    ArrayList<Class> findAllBySemesterAndTime(String semester, String Time);
    ArrayList<Class> findAllBySemesterAndDepartment(String semester, Department department);
    ArrayList<Class> findAllByInstructor(Instructor instructor);
    ArrayList<Class> findAllByStudents(Student student);
    ArrayList<Class> findAllByCourse(Course course);
    ArrayList<Class> findAllByDepartment(Department department);

}
