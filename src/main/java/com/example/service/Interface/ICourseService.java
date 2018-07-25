package com.example.service.Interface;

import com.example.model.Course;

import java.util.List;

public interface ICourseService {

//    view a students transcript
//    enroll a student in a class
//    drop a student from a class
//    view all course
//    view all classes in current semester
//    view all classes in a subject in current semester
//    view all classes by an instructor in a current semester
//    view all classes at a certain time in the current semester
//    view all courses in a department
//    view all current classes in a department
//    view all majors in a department

    List<Course> getAllCourse();
    List<Course> GetAllCoursesByDepartmentID(int departmentId);


}
