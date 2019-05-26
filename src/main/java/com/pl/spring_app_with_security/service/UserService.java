package com.pl.spring_app_with_security.service;

import com.pl.spring_app_with_security.data.dao.UserDAO;
import com.pl.spring_app_with_security.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public void save(User user) {//register
        userDAO.save(user);
    }
}
