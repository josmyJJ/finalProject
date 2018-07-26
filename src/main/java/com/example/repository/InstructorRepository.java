package com.example.repository;

import com.example.model.Instructor;
import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
//    Instructor findByUser(User user);
    Instructor findById(Instructor instructor);
}
