package com.example.service;

import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository UserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository UserRepository){
        this.UserRepository = UserRepository;
    }

    public User findByEmail(String email){
        return UserRepository.findByEmail(email);
    }

    public Long countByEmail(String email){
        return UserRepository.countByEmail(email);
    }

    @Override
    public Long countByUsername(String username) {
        return null;
    }

    public User findByUsername(String username){
        return UserRepository.findByUsername(username);
    }

    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRepository.save(user);
    }
}
