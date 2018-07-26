package com.example.repository;

import com.example.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Subject findBySubjectName(String name);

}
