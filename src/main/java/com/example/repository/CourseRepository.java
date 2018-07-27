package com.example.repository;

import com.example.model.Course;
import com.example.model.Department;
import com.example.model.Major;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CourseRepository extends CrudRepository<Course, Long> {
    ArrayList<Course> findAllByDepartment(Department department);
    Course findByName(String name);
    ArrayList<Course> findAllByMajor(Major major);
}
