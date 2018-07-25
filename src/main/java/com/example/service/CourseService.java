package com.example.service;

import com.example.model.Course;
import com.example.repository.CourseRepository;
import com.example.service.Interface.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();
        courseRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public List<Course> GetAllCoursesByDepartmentID(int departmentId) {
        return null;
    }

}
