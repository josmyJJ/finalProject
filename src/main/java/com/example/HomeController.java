package com.example;

import com.example.beckend.CloudinaryConfig;
import com.example.model.*;
import com.example.model.Class;
import com.example.repository.*;
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


    @RequestMapping("/student")
    public String student(){
        return "student";
    }

    @RequestMapping("/advisor")
    public String advisor(){
        return "advisor";
    }

    @RequestMapping("/instructor")
    public String instructor(){
        return "instructor";
    }

    @RequestMapping("/course")
    public String course(){
        return "course";
    }

    @RequestMapping("/secure")
    public String secure(){
        return "secure";
    }



    @GetMapping("/add")
    public String addcourse(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = UserService.findByUsername(currentusername);
        model.addAttribute("user_id",user.getId());
//        model.addAttribute("imageLabel", "Upload Image");
//        model.addAttribute("message", new Course());
        model.addAttribute("course", new Course());
        return "addcourse";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping("/listdepartment")
    public String listdepartment(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentpage";
    }

    @RequestMapping("/classes/{id}")
    public String classesofDepartments(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id));
        return "classpage";
    }


    //department
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

//    ADD CLASSROOM

    @GetMapping("/addclassroom")
    public String addclassroom(Model model){
        model.addAttribute("classroom", new Classroom());
        return "addclassroom";
    }

    @PostMapping("/processclassroom")
    public String processclassroom(@ModelAttribute("classroom") Classroom classroom, Model model){
        classroomRepository.save(classroom);

        //model.addAttribute("classroom", classroomRepository.findAll());
        //return "index";
        return "redirect:/";
    }

    //Login

    //add class
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

    @RequestMapping("/schedule")
    public String studentSchedul(Model model){
        model.addAttribute("students", studentService.getAllStudents());

        List<Student> students = studentService.getAllStudents();
        for (Iterator<Student> s = students.iterator(); s.hasNext();) {
            Student item = s.next();
            System.out.println(item.getName());
        }
        return "studentschedule";
    }

    protected User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = UserService.findByUsername(currentusername);
        return user;
    }

    //enroll class
    @RequestMapping("/enroll/{id}")
    public String enroll(@PathVariable("id") long id, Model model){
        //model.addAttribute("class", classRepository.findById(id).get());
        model.addAttribute("class", classRepository.findById(id));
        //return "classpage";
        return "index";
    }
    //drop class
    @RequestMapping("/drop/{id}")
    public String drop(@PathVariable("id") long id, Model model){
        classRepository.deleteById(id);
        model.addAttribute(classRepository.findAll());
//        return "index";
        return "redirect:/";
    }

    //student schedule
//    @RequestMapping("/studentschedule")
//    public String stdentschedule(Model model){
//
//        model.addAttribute(classroomRepository.findAll());
//        return "studentschedule";
//    }

    //@RequestMapping("/viewclass")
    @RequestMapping("/studentschedule")
    public String viewClass(Model model) {
        model.addAttribute("classes", classRepository.findAll());
        model.addAttribute("classroom", classroomRepository.findAll());
        //return "classlist";
        return "studentschedule";
    }

}
