package com.example.beckend;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data...");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADVISOR"));
        roleRepository.save(new Role("STUDENT"));
        roleRepository.save(new Role("INSTRUCTOR"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");
        Role advisorRole = roleRepository.findByRole("ADVISOR");
        Role instructorRole = roleRepository.findByRole("INSTRUCTOR");
        Role studentRole = roleRepository.findByRole("STUDENT");


        User user = new
                User("user@user.com", "user", "user", "userlast", true, "user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new
                User("bob@bob.com", "bob", "Bob", "Bobberson", true, "bob");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(studentRole));
        userRepository.save(user);


        user = new
                User("jim@jim.com", "jim", "Jim", "Jimmerson", true, "jim");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(advisorRole));
        userRepository.save(user);


        user = new
                User("tim@tim.com", "tim", "tim", "Timmerson", true, "tim");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(instructorRole));
        userRepository.save(user);

        user = new
                User("admin@adm.com", "pass", "Admin", "User", true, "admin");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new
                User("sam@ev.com","pass","Sam","Everyman",true,"sam");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user);


    }
}
