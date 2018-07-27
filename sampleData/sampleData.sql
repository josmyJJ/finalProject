-- Department
INSERT INTO `harrison_college1`.`department` (`department_id`, `name`) VALUES ('1', 'English');
INSERT INTO `harrison_college1`.`department` (`department_id`, `name`) VALUES ('2', 'CS');
INSERT INTO `harrison_college1`.`department` (`department_id`, `name`) VALUES ('3', 'History');

-- MAJOR
INSERT INTO `major` (`major_id`,`name`,`department_id`) VALUES (1,'Computer Science',2);
INSERT INTO `major` (`major_id`,`name`,`department_id`) VALUES (2,'Literature',1);
INSERT INTO `major` (`major_id`,`name`,`department_id`) VALUES (3,'Statistics',3);
INSERT INTO `major` (`major_id`,`name`,`department_id`) VALUES (4,'Graphic Design',4);
INSERT INTO `major` (`major_id`,`name`,`department_id`) VALUES (5,'Interior Design',4);

-- Course
INSERT INTO `course` (`course_id`,`crn`,`detail`,`name`,`num_of_credits`,`subject_code`,`department_id`,`major_id`) VALUES (1,1245,'Basic English','English',3,'EN102',1,2);
INSERT INTO `course` (`course_id`,`crn`,`detail`,`name`,`num_of_credits`,`subject_code`,`department_id`,`major_id`) VALUES (2,1345,'Advanced English','Literature',4,'EN104',1,2);
INSERT INTO `course` (`course_id`,`crn`,`detail`,`name`,`num_of_credits`,`subject_code`,`department_id`,`major_id`) VALUES (3,1445,'Basic Math','Algebra',3,'MATH01',3,3);
INSERT INTO `course` (`course_id`,`crn`,`detail`,`name`,`num_of_credits`,`subject_code`,`department_id`,`major_id`) VALUES (4,2123,'Intermediate Math','Precalculus',3,'MATH03',3,3);
INSERT INTO `course` (`course_id`,`crn`,`detail`,`name`,`num_of_credits`,`subject_code`,`department_id`,`major_id`) VALUES (6,4567,'Intro to Algoritham','Algoritham',3,'CS234',2,1);

-- Instructor
INSERT INTO `harrison_college1`.`instructor` (`instructor_id`, `employee_number`, `name`, `office_number`, `department_id`) VALUES ('1', '56789', 'Dave Wolf', '345', '2');
INSERT INTO `harrison_college1`.`instructor` (`instructor_id`, `employee_number`, `name`, `office_number`) VALUES ('2', '2345', 'Dem ', '200');

-- Classroom
INSERT INTO `classroom` (`classroom_id`,`building_name`,`max_capacity`,`room_number`) VALUES (1,'Alton Henley',20,'200');
INSERT INTO `classroom` (`classroom_id`,`building_name`,`max_capacity`,`room_number`) VALUES (2,'CBS410',40,'450');
INSERT INTO `classroom` (`classroom_id`,`building_name`,`max_capacity`,`room_number`) VALUES (3,'MSN3',30,'459');

-- Student
INSERT INTO `student` (`student_id`,`name`,`number`,`year_of_entry`,`depart_id`,`major_id`) VALUES (1,'Josemey',565766,'2018',1,1);
INSERT INTO `student` (`student_id`,`name`,`number`,`year_of_entry`,`depart_id`,`major_id`) VALUES (2,'Thinzar',333345,'2017',1,2);
INSERT INTO `student` (`student_id`,`name`,`number`,`year_of_entry`,`depart_id`,`major_id`) VALUES (3,'Chiddimma',455656,'2018',2,3);

-- ClassSQl
INSERT INTO `class` (`class_id`,`course_number`,`crn_number`,`subject_code`,`time`,`classroom_id`,`course_id`,`instructor_id`,`semester_id`) VALUES (1,2345,'101','EN101','9am -10am',1,1,1,NULL);
INSERT INTO `class` (`class_id`,`course_number`,`crn_number`,`subject_code`,`time`,`classroom_id`,`course_id`,`instructor_id`,`semester_id`) VALUES (2,2367,'405','MA56','12pm - 1.15pm',2,2,2,NULL);

-- Semester
INSERT INTO `harrison_college1`.`semester` (`smester_id`, `name`) VALUES ('1', 'Spring');
INSERT INTO `harrison_college1`.`semester` (`smester_id`, `name`) VALUES ('2', 'Summer');
INSERT INTO `harrison_college1`.`semester` (`smester_id`, `name`) VALUES ('3', 'Fall');
INSERT INTO `harrison_college1`.`semester` (`smester_id`) VALUES ('4');

-- Grade
INSERT INTO `harrison_college1`.`grade` (`grade_id`, `course_id`, `instructor_id`, `semester_id`, `student_id`, `grade`) VALUES ('1', '1', '1', '1', '1', 'A');
INSERT INTO `harrison_college1`.`grade` (`grade_id`, `course_id`, `instructor_id`, `semester_id`, `student_id`, `grade`) VALUES ('2', '2', '1', '1', '2', 'A');
INSERT INTO `harrison_college1`.`grade` (`grade_id`, `course_id`, `instructor_id`, `semester_id`, `student_id`) VALUES ('3', '3', '2', '2', '3');



