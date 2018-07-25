package com.example.service.Interface;

import com.example.model.User;

public interface IUserService {
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
    public void saveUser(User user);
}
