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
    SemesterRepository semesterRepository;

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
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("classrooms", classroomRepository.findAll());
        model.addAttribute("classes", classRepository.findAll());
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

    @RequestMapping("/instructor")
    public String instructor(){
        return "instructor";
    }


// ************** CODE FOR REGISTRATION **********************

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

// ******************* CODE FOR DEPARTMENT ********************************

    @RequestMapping("/dpartment")
    public String listdepartment(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentpage";
    }

    @GetMapping("/adddepartment")
    public String adddepartment(Model model){
        model.addAttribute("department", new Department());
        return "adddepartment";
    }

    @PostMapping("/processdepartment")
    public String processdepartment(@ModelAttribute("department") Department department){
        departmentRepository.save(department);
        //return "index";
        return "redirect:/";
    }

// ******************* CODE FOR MAJOR ********************************

    @RequestMapping("/major")
    public String listdeMajor(Model model){
        model.addAttribute("majors", majorRepository.findAll());
        return "major";
    }

    @GetMapping("/addMajor")
//    @RequestMapping(value = "/addMajor", method = RequestMethod.GET)
    public String addMajor(Model model){
        model.addAttribute("departmentList", departmentRepository.findAll());
        model.addAttribute("major", new Major());
        return "addmajor";
    }

    @PostMapping("/processMajor")
    public String processMajor(@ModelAttribute("major") Major major){
        majorRepository.save(major);
        //return "index";
        return "majordetails";
    }


// ******************* CODE FOR COURSE ******************************

    @RequestMapping("/course")
    public String course(){
        return "course";
    }

    @GetMapping("/add")
    public String addcourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departmentList", departmentRepository.findAll());
        model.addAttribute("majorList", majorRepository.findAll());
        return "addcourse";
    }

    @PostMapping("/addCourse")
    public String processCourse(@Valid @ModelAttribute Course course, BindingResult result) {
        if(result.hasErrors()) {
            return "addcourse";
        }
        courseRepository.save(course);
        return "redirect:/";
    }

    @RequestMapping("/coursedetail")
    public String courseDetail(Model model){
        model.addAttribute("courses", courseService.getAllCourse());
        List<Course> course = courseService.getAllCourse();
        for (Iterator<Course> c = course.iterator(); c.hasNext();) {
            Course item = c.next();
            System.out.println(item.getName());
        }
        return "coursedetails";
    }

    @RequestMapping("/updateCourse/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("course", courseRepository.findById(id));
        model.addAttribute("majors", majorRepository.findAll());
//        model.addAttribute("subjects", subjectRepository.findAll());
        return "addcourse";
    }

    @RequestMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id")long id){
        courseRepository.deleteById(id);
        return "course";
    }


// ********************* CODE FOR CLASSROOM **************************

    @GetMapping("/addclassroom")
    public String addclassroom(Model model){
        model.addAttribute("classroom", new Classroom());
        return "addclassroom";
    }

    @PostMapping("/processclassroom")
    public String processclassroom(@ModelAttribute("classroom") Classroom classroom, Model model){
        classroomRepository.save(classroom);
        return "redirect:/";
    }


// ********************* CODE FOR CLASS ****************************

    @RequestMapping("/class")
    public String classPage(Model model){
        return "classpage";
    }

    @GetMapping("/addclass")
    public String addclass(Model model){
        model.addAttribute("aclass", new Class());
        return "addclass";
    }

    @PostMapping("/processclass")
    public String processclass(@ModelAttribute("aclass") Class aclass){
        classRepository.save(aclass);
        //return "index";
        return "redirect:/";
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


// ******************* CODE FOR STUDENT ***********************

    @RequestMapping("/student")
    public String student(){
        return "student";
    }

    @RequestMapping("/schedule")
    public String studentSchedule(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        List<Student> students = studentService.getAllStudents();
        for (Iterator<Student> s = students.iterator(); s.hasNext();) {
            Student item = s.next();
            System.out.println(item.getName());
        }
        return "studentschedule";
    }

    @RequestMapping("/studentschedule")
    public String viewClass(Model model) {
        model.addAttribute("classes", classRepository.findAll());
        model.addAttribute("classroom", classroomRepository.findAll());
        //return "classlist";
        return "studentschedule";
    }


// ******************* CODE FOR ADVISOR ***********************

    @RequestMapping("/advisor")
    public String advisor(){
        return "advisor";
    }

    @RequestMapping("/advisorPage")
    public String advisorPage(Model model){
        model.addAttribute("students", studentService.getAllStudents());

        List<Student> students = studentService.getAllStudents();
        for (Iterator<Student> s = students.iterator(); s.hasNext();) {
            Student item = s.next();
            System.out.println(item.getName());
        }
        return "studentdetails";
    }


// ******************* CODE FOR ADVISOR ***********************
}
