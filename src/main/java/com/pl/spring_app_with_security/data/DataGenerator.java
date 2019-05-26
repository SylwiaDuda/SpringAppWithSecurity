package com.pl.spring_app_with_security.data;

import com.pl.spring_app_with_security.data.dao.UserDAO;
import com.pl.spring_app_with_security.data.entity.User;
import com.pl.spring_app_with_security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(UserService userService, UserDAO userDAO) {
        return args -> {

            if (hasData(userDAO)) {
                return;
            }
            User admin = new User("Admin", "Admin", "Admin", "password");
            userService.registration(admin);
            for (int i = 1; i <= 10; i++) {
                User user = new User("user"+ i, "user"+ i, "user"+ i, "user"+ i);
                userService.registration(user);
            }
        };
    }

    private boolean hasData(UserDAO userDAO) {
        return userDAO.count() != 0L;
    }
}
