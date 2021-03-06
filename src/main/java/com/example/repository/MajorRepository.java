package com.example.repository;

import com.example.model.Department;
import com.example.model.Major;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MajorRepository extends CrudRepository<Major, Long> {
    // Majors in a department
    ArrayList<Major> findAllByDepartment(Department department);
}
