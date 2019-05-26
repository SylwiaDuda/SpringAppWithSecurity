package com.pl.spring_app_with_security.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pl.spring_app_with_security.data.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
}
