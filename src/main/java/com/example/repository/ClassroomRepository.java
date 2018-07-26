package com.example.repository;

import com.example.model.Classroom;
import com.example.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
    ArrayList<Classroom> findAllByCourses(Course course);
}
