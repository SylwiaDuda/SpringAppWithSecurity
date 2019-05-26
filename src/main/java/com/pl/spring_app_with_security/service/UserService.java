package com.pl.spring_app_with_security.service;

import com.pl.spring_app_with_security.data.dao.UserDAO;
import com.pl.spring_app_with_security.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public void registration(User user) {//register
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userDAO.save(user);
    }


}
