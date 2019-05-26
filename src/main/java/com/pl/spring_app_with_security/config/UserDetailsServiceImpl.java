package com.pl.spring_app_with_security.config;

import com.pl.spring_app_with_security.data.entity.User;
import com.pl.spring_app_with_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userService.findUserByLogin(login);
        if (Objects.nonNull(user)) {
            String userLogin = user.getLogin();
            String userPassword = user.getPassword();
            return new org.springframework.security.core.userdetails.User(userLogin, userPassword,
                    Collections.singletonList(new SimpleGrantedAuthority("user")));
        } else {
            throw new UsernameNotFoundException("Nieprawidłowy login lub hasło!");
        }
    }
}


