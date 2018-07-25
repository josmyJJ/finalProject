package com.example.repository;

import com.example.model.Semester;
import org.springframework.data.repository.CrudRepository;

public interface SemesterRepository extends CrudRepository<Semester, Long> {
}
