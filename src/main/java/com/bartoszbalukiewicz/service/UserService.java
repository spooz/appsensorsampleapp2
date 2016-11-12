package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.form.RegisterForm;
import com.bartoszbalukiewicz.model.User;
import com.bartoszbalukiewicz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Bartek on 18.09.2016.
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)  {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(RegisterForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.hashPassword();
        userRepository.save(user);
        return user;
    }
}
