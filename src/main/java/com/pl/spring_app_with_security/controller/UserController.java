package com.pl.spring_app_with_security.controller;

import com.pl.spring_app_with_security.data.entity.User;
import com.pl.spring_app_with_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPagePOST(@ModelAttribute(value = "user") User user) {
        userService.registration(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePage(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", userService.findUserByLogin(name));
        return "profile";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String profilePage(Model m) {
        m.addAttribute("userlist", userService.findAll());
        return "users";
    }
}
