package com.example;

import com.example.beckend.CloudinaryConfig;
import com.example.model.*;
import com.example.model.Class;
import com.example.repository.*;
import com.example.service.Interface.ICourseService;
import com.example.service.Interface.IStudentService;
import com.example.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private IStudentService studentService;

    // CHANGES I MADE
    @Autowired
    private ICourseService courseService;

    @Autowired
    private SubjectRepository subjectRepository;


    @Autowired
    private IUserService UserService;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MajorRepository majorRepository;

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/search")
    public String search() {
        return "search";
    }


// ********************** REGISTRATION **********************

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model){
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "registration";
        }else{
            UserService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index";
    }

    protected User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = UserService.findByUsername(currentusername);
        return user;
    }

// ************************ DEPARTMENT ********************************

    @RequestMapping("/department")
    public String listdepartment(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "department/department";
    }

    @GetMapping("/adddepartment")
    public String adddepartment(Model model){
        model.addAttribute("department", new Department());
        return "department/adddepartment";
    }

    @PostMapping("/processdepartment")
    public String processdepartment(@ModelAttribute("department") Department department){
        departmentRepository.save(department);
        //return "index";
        return "admin";
    }

    @RequestMapping("/listDepartment")
    public String viewAllDepartment(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "department/departments";
    }

    @RequestMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model) {
        model.addAttribute("department", departmentRepository.findById(id));
        return "department/adddepartment";
    }

    @RequestMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id")long id){
        departmentRepository.deleteById(id);
        return "admin";
    }


// **************************** MAJOR ********************************

    @RequestMapping("/major")
    public String listdeMajor(Model model){
        model.addAttribute("majors", majorRepository.findAll());
        return "major/major";
    }

    @GetMapping("/addMajor")
//    @RequestMapping(value = "/addMajor", method = RequestMethod.GET)
    public String addMajor(Model model){
        model.addAttribute("major", new Major());
        model.addAttribute("departments", departmentRepository.findAll());
        return "major/addmajor";
    }

    @PostMapping("/processMajor")
    public String processMajor(@ModelAttribute Major major){
        majorRepository.save(major);
        //return "index";
        return "major/major";
    }

    @RequestMapping("/listMajor")
    public String listMajors(Model model) {
        model.addAttribute("majors", majorRepository.findAll());
        return "major/majordetails";
    }

    @PostMapping("/majorsByDepartment")
    public String majorsByDepartment(Model model, @RequestParam("departmentName") String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        model.addAttribute("majors", majorRepository.findAllByDepartment(department));
        return "major/majors";
    }

    @RequestMapping("/updateMajor/{id}")
    public String updateMajor(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("major", majorRepository.findById(id));
        model.addAttribute("departments", departmentRepository.findAll());
        return "major/addmajor";
    }

    @RequestMapping("deleteMajor/{id}")
    public String deleteMajor(@PathVariable("id") long id){
        majorRepository.deleteById(id);
        return "major/major";

    }


// ************************** COURSE ******************************

    @RequestMapping("/course")
    public String course(){
        return "course/course";
    }

    @GetMapping("/add")
    public String addcourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departmentList", departmentRepository.findAll());
        model.addAttribute("majorList", majorRepository.findAll());
        return "course/addcourse";
    }

    @PostMapping("/addCourse")
    public String processCourse(@ModelAttribute Course course, BindingResult result) {
        if(result.hasErrors()) {
            return "course/addcourse";
        }
        courseRepository.save(course);
        return "course/course";
    }

    @RequestMapping("/coursedetail")
    public String courseDetail(Model model){
        model.addAttribute("courses", courseService.getAllCourse());
        List<Course> course = courseService.getAllCourse();
        for (Iterator<Course> c = course.iterator(); c.hasNext();) {
            Course item = c.next();
            System.out.println(item.getName());
        }
        return "course/coursedetails";
    }

    @PostMapping("/coursesByDepartment")
    public String coursesByDepartment(Model model, @RequestParam("department_name") String department_name) {
        Department department = departmentRepository.findByDepartmentName(department_name);
        model.addAttribute("courses", courseRepository.findAllByDepartment(department));
        return "course/courses";
    }

    @RequestMapping("/updateCourse/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("course", courseRepository.findById(id));
        model.addAttribute("majors", majorRepository.findAll());
//        model.addAttribute("subjects", subjectRepository.findAll());
        return "course/addcourse";
    }

    @RequestMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id")long id){
        courseRepository.deleteById(id);
        return "course/course";
    }


// ************************** CLASSROOM **************************

    @RequestMapping("/classroom")
    public String classroom(Model model){
        return "classroom/classrooms";
    }

    @GetMapping("/addclassroom")
    public String addclassroom(Model model){
        model.addAttribute("classroom", new Classroom());
        return "classroom/addclassroom";
    }

    @PostMapping("/processclassroom")
    public String processclassroom(@ModelAttribute("classroom") Classroom classroom, Model model){
        classroomRepository.save(classroom);
        return "classroom/classrooms";
    }

    @RequestMapping("/listClassroom")
    public String listClassrooms(Model model) {
        model.addAttribute("classrooms", classroomRepository.findAll());
        return "classroom/classroomdetails";
    }

    @RequestMapping("/updateClassroom/{id}")
    public String updateClassroom(@PathVariable("id") long id, Model model)
    {
        // model.addAttribute("course", courseRepository.findById(id));
        // model.addAttribute("majors", majorRepository.findAll());
//        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("classroom", classroomRepository.findById(id));
        return "classroom/addclassroom";
    }

    @RequestMapping("/deleteClassroom/{id}")
    public String deleteClassroom(@PathVariable("id")long id){
        classroomRepository.deleteById(id);
        return "classroom/classrooms";
    }



// ************************** CLASS ****************************

    @RequestMapping("/class")
    public String classPage(Model model){
        return "class/classpage";
    }

    @GetMapping("/addclass")
    public String addclass(Model model){
        model.addAttribute("aclass", new Class());
        return "class/addclass";
    }

    @PostMapping("/processclass")
    public String processclass(@ModelAttribute("aclass") Class aclass){
        classRepository.save(aclass);
        //return "index";
        return "class/classpage";
    }

    @RequestMapping("/listClass")
    public String viewAllClasses(Model model) {
        model.addAttribute("classes", classRepository.findAll());
        return "class/allclasses";
    }

    @RequestMapping("/enroll/{id}")
    public String enroll(@PathVariable("id") long id, Model model){
        //model.addAttribute("class", classRepository.findById(id).get());
        model.addAttribute("class", classRepository.findById(id));
        //return "classpage";
        return "index";
    }

    @RequestMapping("/drop/{id}")
    public String drop(@PathVariable("id") long id, Model model){
        classRepository.deleteById(id);
        model.addAttribute(classRepository.findAll());
        return "redirect:/";
    }


// ************************** STUDENT *************************

    @RequestMapping("/student")
    public String student(){
        return "student/student";
    }

    @RequestMapping("/schedule")
    public String studentSchedule(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        List<Student> students = studentService.getAllStudents();
        for (Iterator<Student> s = students.iterator(); s.hasNext();) {
            Student item = s.next();
            System.out.println(item.getName());
        }
        return "student/studentschedule";
    }

    @RequestMapping("/studentschedule")
    public String viewClass(Model model) {
        model.addAttribute("classes", classRepository.findAll());
        model.addAttribute("classroom", classroomRepository.findAll());
        //return "classlist";
        return "student/studentschedule";
    }


// ******************* CODE FOR ADVISOR ***********************

    @RequestMapping("/advisor")
    public String advisor(){
        return "advisor/advisor";
    }

    @RequestMapping("/advisorPage")
    public String advisorPage(Model model){
        model.addAttribute("students", studentService.getAllStudents());

        List<Student> students = studentService.getAllStudents();
        for (Iterator<Student> s = students.iterator(); s.hasNext();) {
            Student item = s.next();
            System.out.println(item.getName());
        }
        return "student/studentdetails";
    }


// ******************* CODE FOR INSTRUCTOR ***********************

    @RequestMapping("/instructor")
    public String instructor(){
        return "instructor/instructor";
    }

    @GetMapping("/addinstructor")
    public String addinstructor(Model model){
        model.addAttribute("instructor", new Instructor());
        return "instructor/addinstructor";
    }

    @PostMapping("/processinstructor")
    public String processinstructor(@ModelAttribute("instructor") Instructor instructor, Model model){
        instructorRepository.save(instructor);
        return "redirect:/";
    }




}
