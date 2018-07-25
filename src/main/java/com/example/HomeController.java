package com.example;

import com.example.beckend.CloudinaryConfig;
import com.example.model.Course;
import com.example.model.Student;
import com.example.model.User;
import com.example.repository.CourseRepository;
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

    @RequestMapping("/")
    public String index(){

//        model.addAttribute("courses", courseRepository.findAll());
//
//        model.addAttribute("students", studentService.getAllStudents());
//        List<Student> students = studentService.getAllStudents();
//        for (Iterator<Student> s = students.iterator(); s.hasNext();) {
//            Student item = s.next();
//            System.out.println(item.getName());
//        }
//        return "student";
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
    public String messageForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = UserService.findByUsername(currentusername);
        model.addAttribute("user_id",user.getId());
//        model.addAttribute("imageLabel", "Upload Image");
//        model.addAttribute("message", new Course());
        model.addAttribute("course", new Course());
        return "messageform";
    }

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
}
