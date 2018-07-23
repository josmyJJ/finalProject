package com.example;

import com.cloudinary.utils.ObjectUtils;
import com.example.beckend.CloudinaryConfig;
import com.example.beckend.UserService;
import com.example.model.Course;
import com.example.model.Pet;
import com.example.model.User;
import com.example.repository.CourseRepository;
import com.example.repository.PetRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/")
    public String index(@ModelAttribute Pet message, Model model){
        model.addAttribute("messages", petRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
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
        User user = userRepository.findByUsername(currentusername);
        model.addAttribute("user_id",user.getId());
//        model.addAttribute("imageLabel", "Upload Image");
        model.addAttribute("message", new Course());
        model.addAttribute("course", new Course());

        return "messageform";
    }



    @PostMapping("/process")
    public String processForm(HttpServletRequest request, @Valid @ModelAttribute Course course, BindingResult result
//                              @RequestParam("file") MultipartFile file
    ) {

//        @RequestParam("hiddenImgURL") String ImgURL
        User user = getUser();

//        if (file.isEmpty()) {
//            return "redirect:/add";
//        }
//        if (!file.isEmpty()) {
//            try {
//                Map uploadResult = cloudc.upload(file.getBytes(),
//                        ObjectUtils.asMap("resourcetype", "auto"));
//                message.setPostImg(uploadResult.get("url").toString());
//
////                user.setHash(user.getEmail());
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "redirect:/add";
//            }
////            catch (NoSuchAlgorithmException e) {
////                e.printStackTrace();
////                return "redirect:/add";
////            }
//        }
//        else {
//            if (!ImgURL.isEmpty()) {
//                message.setPostImg(ImgURL);
//            } else {
//                message.setPostImg("");
//            }
//        }

//        message.setUser(user);
//        message.setPosteddate();
        courseRepository.save(course);
        return "redirect:/";
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
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("message", petRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@ModelAttribute Pet message, @PathVariable
            ("id") long id, Model model) {
        message = petRepository.findById(id).get();
        model.addAttribute("message", petRepository.findById(id));
        model.addAttribute("imageURL", message.getPostImg());

        if(message.getPostImg().isEmpty()) {
            model.addAttribute("imageLabel", "Upload Image");
        }
        else {
            model.addAttribute("imageLabel", "Upload New Image");
        }
        return "messageform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        petRepository.deleteById(id);
        return "redirect:/";
    }

    protected User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = userRepository.findByUsername(currentusername);
        return user;
    }
}
