package com.example.repository;

import com.example.model.Instructor;
import com.example.model.Student;
import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StudentRepository extends CrudRepository<Student, Long> {
    ArrayList<Student> findAllByInstructor(Instructor Instructor);

//    Student findByUser(User user);
}
